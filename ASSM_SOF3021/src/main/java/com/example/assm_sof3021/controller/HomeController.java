package com.example.assm_sof3021.controller;

import com.example.assm_sof3021.modal.*;
import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import com.example.assm_sof3021.service.impl.*;
import com.example.assm_sof3021.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class HomeController {
    @Autowired
    private CardService cardService;
    @Autowired
    private CategorieService cateService;
    @Autowired
    private UserService usService;
    @Autowired
    private CartSessionService cartService;
    @Autowired
    private CartService cartSV;

    @Autowired
    private BillcardService billcardService;
    @Autowired
    private EmailService emailService;

    @RequestMapping("/suppot")
    public String spsendmail(
            @RequestParam(defaultValue = "", name = "idOrder") String idOrder,
            @RequestParam(defaultValue = "", name = "idOrderr") String idOrderr,
            Model model,HttpSession session
    ) {
        List<BillCard> l= billcardService.finbyidorder(Integer.parseInt(idOrder));
        if(l.size()==0){
            session.setAttribute("error", "Không Tìm Thấy Hóa Đơn");
            return "redirect:/user/home";
        }
        if(idOrderr.equals("")){
            model.addAttribute("bill",l.get(0));
            return "/user/suppot";
        }else{
            emailService.SendEmail("smtp.gmail.com","587", "cuongndph14605@fpt.edu.vn",  "Duycuong1",l.get(0).getCarts(),Integer.parseInt(idOrderr),"sendCard");
            session.setAttribute("success", "Thẻ Đã Được Gửi Lại Đến Email");
            return "redirect:/user/home";
        }

    }


    @RequestMapping("/home")
    public String home(@RequestParam(defaultValue = "all", name = "classfy") String classfy,
                       @RequestParam(defaultValue = "all", name = "value") String value,
                       Model model, HttpSession session
    ) {
        String classfyy = "";
        if (!classfy.equals("all")) {
            classfyy = classfy;

            if (!value.equals("all")) {
                List<Card> list = cardService.listCardCon(Status.STT0, ClassifyCard.valueOf(classfy), ValueCard.valueOf(value));
                if (list.size() == 0) {
                    session.setAttribute("error", "Thẻ này hiện tai đang hết bạn hãy chọn thẻ khác");
                } else {


                }
            }
        }
        model.addAttribute("classfyy", classfyy);
        model.addAttribute("classfy", classfy);
        model.addAttribute("value", value);
        return "user/home";
    }

    @RequestMapping("/cart")
    public String cart(
            Model model, HttpSession session
    ) {
        List<CartSession> list = cartService.listcartss(session);
        model.addAttribute("list", list);
        return "/user/cart";
    }

    @RequestMapping("/deletecart")
    public String deletecart(
            @RequestParam(name = "id") String id,
            Model model, HttpSession session
    ) {
        List<CartSession> list = cartService.listcartss(session);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == Integer.parseInt(id)) {
                list.remove(list.get(i));
                session.setAttribute("listcardss", list);
                session.setAttribute("success", "Xóa khỏi giỏ hàng thành công");
            }
        }

        return "redirect:/user/cart";

    }

    @RequestMapping("/cartbill")
    public String cartbill(@RequestParam(defaultValue = "", name = "price") String price,
                           @RequestParam(defaultValue = "all", name = "quantity") String quantity,
                           @RequestParam(defaultValue = "", name = "id") String id,
                           Model model, HttpSession session
    ) {

        int idss = Integer.parseInt(id);
        Double d = Double.parseDouble(price);
        Float pricebi = Float.parseFloat(price) * Float.parseFloat(quantity);
        BigDecimal pricebil = BigDecimal.valueOf(pricebi);
        String pricebill = pricebil.intValue() + "";
        if (pricebil.intValue() < 10000 || pricebil.intValue() > 1000000000) {
            session.setAttribute("error", "Hiện tại ngân hàng chỉ cho phép thanh toán đơn từ 10 nghìn đến 1 tỷ. Bạn hãy tùy chỉnh quantity và thực hiện lại");
            return "redirect:/user/cart";
        }

        List<CartSession> list = cartService.listcartss(session);
        for (int i = 0; i < list.size(); i++) {
            if (idss == list.get(i).getId()) {
                model.addAttribute("bill", list.get(i));

            }
        }
        String stringPrice=billcardService.stringPrice(Integer.parseInt(pricebill));
        model.addAttribute("quantity", quantity);
        model.addAttribute("stringPrice", stringPrice);
        model.addAttribute("pricebill", pricebill);
        model.addAttribute("list", list);

        return "/user/cartbill";

    }


    @RequestMapping("/addcart")
    public String addcart(@RequestParam(defaultValue = "all", name = "classfy") String classfy,
                          @RequestParam(defaultValue = "all", name = "value") String value,
                          @RequestParam(defaultValue = "all", name = "mailSend") String mailSend,
                          Model model, HttpSession session
    ) {


        if (classfy.equals("all")) {
            session.setAttribute("error", "Bạn cần chọn loại thẻ ở bước 1.");
            return "redirect:/user/home";

        } else {
            if (value.equals("all")) {
                session.setAttribute("error", "Bạn cần chọn mệnh giá thẻ ở bước 2.");
                return "redirect:/user/home";
            } else {
                if (mailSend.equals("all")) {
                    session.setAttribute("error", "Bạn phải nhập mail nhận ở bước 3.");
                    return "redirect:/user/home";
                } else {
                    List<CartSession> listcards = cartService.listcartss(session);
                    if (listcards == null) {
                        listcards = new ArrayList<>();
                    } else {
                        listcards = cartService.listcartss(session);
                    }
                    List<Categories> listcate =
                            cateService.findByClassifyAndValue(ClassifyCard.valueOf(classfy), ValueCard.valueOf(value));
                    int id = listcate.get(0).getId();
                    for (int i = 0; i < listcards.size(); i++) {
                        if (listcards.get(i).getId() == id) {
                            if (listcards.get(i).getQuantity() == listcards.get(i).getSlcon()) {
                                session.setAttribute("error", "Số thẻ trong giỏ hàng đã max");
                                return "redirect:/user/cart";
                            }
                            listcards.get(i).setQuantity(listcards.get(i).getQuantity() + 1);

                            session.setAttribute("success", "Thêm vào giỏ hàng thành công");
                            session.setAttribute("listcardss", listcards);
                            return "redirect:/user/cart";
                        }
                    }
                    List<Card> listcardcon =
                            cardService.listCardCon(Status.STT0, ClassifyCard.valueOf(classfy), ValueCard.valueOf(value));


                    CartSession cardss = new CartSession();
                    int slcon = listcardcon.size();
                    BigDecimal price = listcate.get(0).getPrice();

                    String name = listcate.get(0).getName();
                    cardss.setId(id);
                    cardss.setIdCategori(id);
                    cardss.setIdUser(null);
                    cardss.setName(name);
                    cardss.setEmailSend(mailSend);
                    cardss.setSlcon(slcon);
                    cardss.setPrice(price);
                    cardss.setQuantity(1);

                    listcards.add(cardss);
                    session.setAttribute("listcardss", listcards);
                    session.setAttribute("success", "Thêm vào giỏ hàng thành công");
                    return "redirect:/user/cart";

                }
            }
        }


    }

    @RequestMapping("/login")
    public String addcart(
            @RequestParam(defaultValue = "", name = "tk") String tk,
            @RequestParam(defaultValue = "", name = "mk") String mk,

            Model model, HttpSession session
    ) {

        if (tk.equals("")) {
            session.setAttribute("error", "Tài khoản không được bỏ trống");
            return "redirect:/user/home";


        } else {
            if (mk.equals("")) {
                session.setAttribute("error", "Mật Khẩu không được bỏ trống");
                return "redirect:/user/home";
            } else {
                Users us = usService.findtk(tk);
                if (us == null) {
                    session.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");
                    return "redirect:/user/home";
                } else {
                    boolean checkpass = EncryptUtil.checkpass(mk, us.getPassWord());
                    if (checkpass == false) {
                        session.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");
                        return "redirect:/user/home";
                    } else {
                        List<CartService> listcartss = new ArrayList<>();
                        session.setAttribute("listcartss", listcartss);
                        session.setAttribute("userdn", us);
                        session.setAttribute("success", "Đăng Nhập Thành Công");
                        return "redirect:/user/home";
                    }
                }
            }
        }


    }



    @RequestMapping("/pay/return")
    public String returnpay(
            @RequestParam(defaultValue = "", name = "vnp_TransactionStatus") String vnp_TransactionStatus,
            @RequestParam(defaultValue = "", name = "vnp_TxnRef") String vnp_TxnRef,
            Model model, HttpSession session
    ) {
        if (vnp_TransactionStatus.equals("00")) {

            cartSV.update(Integer.parseInt(vnp_TxnRef));
            List<Cart> cc = cartSV.findByidOder(Integer.parseInt(vnp_TxnRef));
            List<Card> cartcon = cardService.findByidcate(cc.get(0).getCategories().getId(), Status.STT0);

            for (int i = 0; i < cc.get(0).getQuantity(); i++) {
                cartcon.get(i).setStatus(Status.STT1);
                cardService.updateby(cartcon.get(i));
                BillCard bill= new BillCard();
                bill.setCards(cartcon.get(i));
                bill.setCarts(cc.get(0));
                billcardService.add(bill);

            }
            emailService.SendEmail("smtp.gmail.com","587", "cuongndph14605@fpt.edu.vn",  "Duycuong1", cc.get(0),cc.get(0).getIdOrder(),"sendCard");

            session.setAttribute("success", "Thanh Toán Thành Công. Mã Hóa Đơn Của Bạn Là: "+cc.get(0).getIdOrder()+" Hãy Lưu Số Này Để Được Hỗ Trợ");
        } else {
            session.setAttribute("error", "Thanh Toán Thất bại");
            List<Cart> cc = cartSV.findByidOder(Integer.parseInt(vnp_TxnRef));
            cartSV.delete(Integer.parseInt(vnp_TxnRef));
        }
        return "redirect:/user/home";
    }


@RequestMapping("/logout")
    public String logout(HttpSession session){
        if(session.getAttribute("userdn")==null){
            session.setAttribute("error", "Bạn Cần Đăng Nhập Mới Có Thể Đăng Xuất");
            return "redirect:/user/home";
        }else {
            session.setAttribute("listcartss", null);
            session.setAttribute("userdn", null);
            session.setAttribute("success", "Đăng Xuất Thành Công");
            return "redirect:/user/home";
        }
    }


    @RequestMapping("/demo")
    public String demoStringPrice(
            @RequestParam Integer id
    ){
        billcardService.stringPrice(id);
        return "";
    }

}
