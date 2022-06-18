package com.example.assm_sof3021.service.impl;

import com.example.assm_sof3021.modal.BillCard;
import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.modal.CardSend;
import com.example.assm_sof3021.service.SendMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.assm_sof3021.utils.SendMail.sendEmail;

@Service
public class EmailService implements SendMailService {
    @Autowired
    BillcardService billcardService;
    private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Online Entertainment";
    private static final String EMAIL_FORGOT_PASSWORD = "Online Entertainment - New password";

    @Override
    public void SendEmail(String host, String port, String user, String pass, Cart cart, Integer idOrder, String type) {
        host = "smtp.gmail.com";
        port = "587";
        user = "cuongndph14605@fpt.edu.vn";
        pass = "Duycuong1";

        try {
            String content = "Thẻ Bảo Lưu";
            String subject = null;
            switch (type) {
                case "sendCard":
                    List<CardSend> list=new ArrayList<>();
                    List<BillCard> listbill=
                    billcardService.finbyidorder(idOrder);
                    for(int i=0;i<listbill.size();i++){
                        CardSend cs= new CardSend();
                        cs.setSTT(i+1);
                        cs.setMadonhang(idOrder);
                        cs.setTenthe(listbill.get(i).getCarts().getCategories().getName());
                        cs.setSeri(listbill.get(i).getCards().getSeri());
                        cs.setMathe(listbill.get(i).getCards().getNumber());
                        cs.setNgayhethan(listbill.get(i).getCards().getExpiry());
                        list.add(cs);
                    }
                    subject = EMAIL_WELCOME_SUBJECT;
                    content = "Dear " + cart.getEmailSend() + " , the cua ban la! \n"+ list;
                    break;
                default:
                    subject = "Online Entertainment";
                    content = "Maybe this email is wrong, don't care about it";
            }
            sendEmail(host, port, user, pass, cart.getEmailSend(), subject, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
