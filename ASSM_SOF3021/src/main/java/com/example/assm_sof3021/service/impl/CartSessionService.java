package com.example.assm_sof3021.service.impl;

import com.example.assm_sof3021.modal.CartSession;
import com.example.assm_sof3021.service.ICartSeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CartSessionService implements ICartSeccionService {

    @Override
    public List<CartSession> listcartss(HttpSession session) {
         List<CartSession> list=
                (List<CartSession>) session.getAttribute("listcardss");
        return list;
    }
}
