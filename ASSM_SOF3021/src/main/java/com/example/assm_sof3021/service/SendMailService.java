package com.example.assm_sof3021.service;

import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.modal.CardSend;

import java.util.List;

public interface SendMailService {
    void SendEmail(String host, String port, String user, String pass, Cart cart, Integer idOrder, String type);
}
