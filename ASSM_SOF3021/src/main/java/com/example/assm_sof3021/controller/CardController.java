package com.example.assm_sof3021.controller;

import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Categories;
import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import com.example.assm_sof3021.service.impl.CardService;
import com.example.assm_sof3021.service.impl.CartService;
import com.example.assm_sof3021.service.impl.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/card")
public class CardController {
@Autowired
private CardService serviceCard;
@Autowired
    private CategorieService serviceCate;
@Autowired
    private CartService serviceCart;

    @RequestMapping("/getall")
    public String getAll(@RequestParam(name = "classify", defaultValue = "all", required = false) String classify, @RequestParam(name = "value", defaultValue = "all", required = false) String value,
                         Model model, @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber
            , @RequestParam(name = "status", defaultValue = "STT0", required = false) String status,
                         HttpSession session
    ) {
//
        Date a = new Date();
        List<Card> l = serviceCard.getAllfull();
        for (int i =0;i<l.size();i++){
            if(a.after(l.get(i).getExpiry())){
                serviceCard.cardExpired(l.get(i).getId());
            }
        }
        List<Categories> listcate = serviceCate.getAll();

            List<ValueCard> listvalue = List.of(ValueCard.values());
            List<ClassifyCard> listclassify = List.of(ClassifyCard.values());
            model.addAttribute("listcate", listcate);
            model.addAttribute("listvalue", listvalue);
            model.addAttribute("listclassify", listclassify);
            Page<Card> Catepage = serviceCard
                    .getByPage(pageNumber, 10,classify,value,status,session);
            model.addAttribute("page", Catepage);





        return "admin/card/getall";

    }

    @RequestMapping("/add")
    public String add(@RequestParam(name="date") String date,
                      @RequestParam(name="categories") String categories,

                      Model model, Card card, HttpSession session) {
        try {
            Date a= new Date();
            System.out.println(a);
            Date dateadd= new SimpleDateFormat("yyyy-MM-dd").parse(date);
            if(a.after(dateadd)){
                session.setAttribute("error", "Ngày Hết Hạn Phải Đứng Sau Ngày Hôm Nay");
                return "redirect:/admin/card/getall";
            }

                card.setExpiry(dateadd);
                Card cardadd = serviceCard.add(card);
                session.setAttribute("success", "Thêm Mới Thành Công");



        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Thêm Mới Thất Bại");
        }


        return "redirect:/admin/card/getall";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id, HttpSession session) {
        try {
            serviceCard.delete(id);
            session.setAttribute("success", "Xóa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
        }
        return "redirect:/admin/card/getall";
    }

    @RequestMapping("/update")
    public String update(@RequestParam String date,Model model, Card cardupdate, HttpSession session, @RequestParam Integer id) {
        try {
            Date dateud= new SimpleDateFormat("yyyy-MM-dd").parse(date);
            cardupdate.setExpiry(dateud );
            Card card =
                    serviceCard.update(cardupdate, id);
            session.setAttribute("success", "Cập Nhật Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Cập Nhật Thất Bại");
        }

        return "redirect:/admin/card/getall";
    }

    @RequestMapping("/deleteclick")
    public String deletetick(@RequestParam("cbdelete") Integer[] idcb, HttpSession session) {
        try {
            List<Card> listcard = serviceCard.getAll(Status.STT0);
            for (int i = 0; i < listcard.size(); i++) {
                for (int y = 0; y < idcb.length; y++) {
                    if (listcard.get(i).getId() == idcb[y]) {
                        serviceCate.delete(listcard.get(i).getId());
                    }
                }

            }
            System.out.println(idcb.length);
            session.setAttribute("success", "Xóa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
        }

        return "redirect:/admin/card-getall";

    }
}
