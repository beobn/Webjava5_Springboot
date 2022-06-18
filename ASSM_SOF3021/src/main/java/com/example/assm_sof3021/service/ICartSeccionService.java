package com.example.assm_sof3021.service;

import com.example.assm_sof3021.modal.CartSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface ICartSeccionService {
    public List<CartSession> listcartss(HttpSession session);
}
