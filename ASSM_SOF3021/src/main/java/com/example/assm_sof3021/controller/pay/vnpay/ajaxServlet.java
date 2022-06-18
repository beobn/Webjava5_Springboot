package com.example.assm_sof3021.controller.pay.vnpay;


import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.modal.CartSession;
import com.example.assm_sof3021.modal.Users;
import com.example.assm_sof3021.service.impl.CartService;
import com.example.assm_sof3021.service.impl.CartSessionService;
import com.example.assm_sof3021.service.impl.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class ajaxServlet {
    @Autowired
    private CartSessionService cartService;
    @Autowired
    private CartService cartSV;
    @Autowired
    private CategorieService catetSV;


    @RequestMapping("/pay/bill")
    public String ajaxServlett(
            @RequestParam(name = "vnp_OrderInfo", defaultValue = "Thanh toan bill", required = false) String vnp_OrderInfo,
            @RequestParam(name = "orderType", defaultValue = "topup", required = false) String orderType,
            @RequestParam(name = "amountt", defaultValue = "", required = false) String amountt,
            @RequestParam(name = "bank_code", defaultValue = "NCB", required = false) String bank_code,
            @RequestParam(name = "language", defaultValue = "vn", required = false) String language,
            @RequestParam(name = "txt_billing_mobile", defaultValue = "", required = false) String txt_billing_mobile,
            @RequestParam(name = "txt_billing_fullname", defaultValue = "", required = false) String txt_billing_fullname,
            @RequestParam(name = "txt_billing_email", defaultValue = "", required = false) String txt_billing_email,
            @RequestParam(name = "txt_inv_addr1", defaultValue = "", required = false) String txt_inv_addr1,
            @RequestParam(name = "txt_bill_city", defaultValue = "", required = false) String txt_bill_city,
            @RequestParam(name = "txt_bill_country", defaultValue = "", required = false) String txt_bill_country,
            @RequestParam(name = "txt_inv_mobile", defaultValue = "", required = false) String txt_inv_mobile,
            @RequestParam(name = "txt_bill_state", defaultValue = "", required = false) String txt_bill_state,
            @RequestParam(name = "txt_inv_email", defaultValue = "", required = false) String txt_inv_email,
            @RequestParam(name = "txt_inv_customer", defaultValue = "", required = false) String txt_inv_customer,
            @RequestParam(name = "txt_inv_company", defaultValue = "", required = false) String txt_inv_company,
            @RequestParam(name = "txt_inv_taxcode", defaultValue = "", required = false) String txt_inv_taxcode,
            @RequestParam(name = "cbo_inv_type", defaultValue = "", required = false) String cbo_inv_type,
            @RequestParam(name = "id", defaultValue = "", required = false) String id,
            @RequestParam(name = "quantity", defaultValue = "", required = false) String quantity,
            HttpServletRequest request, HttpSession session

            ) throws IOException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = com.example.assm_sof3021.controller.pay.vnpay.Config.getRandomNumber(8);
        String vnp_IpAddr = com.example.assm_sof3021.controller.pay.vnpay.Config.getIpAddress(request);
        String vnp_TmnCode = Config.vnp_TmnCode;


        int amount = 1000000000;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", amountt+"00");
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bank_code != null && !bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);
        String locate = language;
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.0.1 Version
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        //Billing
        vnp_Params.put("vnp_Bill_Mobile", txt_billing_mobile);
        vnp_Params.put("vnp_Bill_Email", txt_billing_email);
        String fullName = txt_billing_fullname.trim();
        if (fullName != null && !fullName.isEmpty()) {
            int idx = fullName.indexOf(' ');
            String firstName = fullName.substring(0, idx);
            String lastName = fullName.substring(fullName.lastIndexOf(' ') + 1);
            vnp_Params.put("vnp_Bill_FirstName", firstName);
            vnp_Params.put("vnp_Bill_LastName", lastName);

        }
        vnp_Params.put("vnp_Bill_Address", txt_inv_addr1);
        vnp_Params.put("vnp_Bill_City", txt_bill_city);
        vnp_Params.put("vnp_Bill_Country", txt_bill_country);
        if (txt_bill_state != null && txt_bill_state.isEmpty()) {
            vnp_Params.put("vnp_Bill_State", txt_bill_state);
        }
        // Invoice
        vnp_Params.put("vnp_Inv_Phone", txt_inv_mobile);
        vnp_Params.put("vnp_Inv_Email", txt_inv_email);
        vnp_Params.put("vnp_Inv_Customer", txt_inv_customer);
        vnp_Params.put("vnp_Inv_Address", txt_inv_addr1);
        vnp_Params.put("vnp_Inv_Company", txt_inv_company);
        vnp_Params.put("vnp_Inv_Taxcode", txt_inv_taxcode);
        vnp_Params.put("vnp_Inv_Type", cbo_inv_type);
        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                try {
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
//ss
        int idss=Integer.parseInt(id);
        List<CartSession> list = cartService.listcartss(session);
        Cart c = new Cart();

        c.setQuantity(Integer.parseInt(quantity));

        for(int i=0;i<list.size();i++){

            if( list.get(i).getId()==idss){

                c.setEmailSend(list.get(i).getEmailSend());

            }
            else{

            }
        }
        Users us = (Users) session.getAttribute("userdn");
        if(us==null){
            c.setUsers(null);

        }
        else c.setUsers(us);
       c.setCategories(catetSV.findbyid(idss).get());

        c.setIdOrder(Integer.parseInt(vnp_TxnRef));
        c.setPriceOrder(BigDecimal.valueOf(Double.parseDouble(amountt)));
        cartSV.add(c);
        if(list.size()==1){
            session.removeAttribute("listcardss");
        }
        else{
            for(int i=0;i<list.size();i++){

                if( list.get(i).getId()==idss){

                    list.remove(list.get(i));

                }
                else{

                }
            }
        }



        return "redirect:"+paymentUrl;

    }




}
