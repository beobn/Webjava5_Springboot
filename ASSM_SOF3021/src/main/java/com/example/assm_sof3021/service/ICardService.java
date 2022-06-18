package com.example.assm_sof3021.service;


import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Categories;
import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface ICardService {

    public List<Card> getAll(Status status);
    public List<Card> getAllfull();
    public List<Card> findByidcate(Integer idcate,Status status);
    public List<Card> listCardCon(Status status,ClassifyCard classify,ValueCard value);
    public Card add(Card card);
    public Card delete(Integer id);
    public Card cardExpired (Integer id);
    public Card update(Card card, Integer id);
    public Card updateby(Card card);





    //    ------mượn của thầy------
    Page<Card> getByPage(int pageNumber, int maxRecord, String classify, String value, String status, HttpSession session);


}
