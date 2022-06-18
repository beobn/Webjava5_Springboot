package com.example.assm_sof3021.service.impl;


import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import com.example.assm_sof3021.repository.ICard;
import com.example.assm_sof3021.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CardService implements ICardService {
    @Autowired
    ICard repository;
    @Override
    public List<Card> getAll(Status status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Card> getAllfull() {
        return repository.findAll();
    }

    @Override
    public List<Card> findByidcate(Integer idcate, Status status) {

        return repository.findByStatusAndCategories_Id(status,idcate);
    }

    @Override
    public List<Card> listCardCon(Status status, ClassifyCard classify, ValueCard value) {

        return repository.findByCategories_ClassifyAndCategories_ValueAndStatus(classify,value,status);
    }

    @Override
    public Card add(Card card) {
        card.setId(null);
        card.setStatus(Status.STT0);
        return repository.save(card);
    }

    @Override
    public Card delete(Integer id) {
        Card c = repository.findById(id).get();
        c.setStatus(Status.STT1);
        return repository.save(c);
    }

    @Override
    public Card cardExpired(Integer id) {
        Card c= repository.findById(id).get();
        c.setStatus(Status.STT3);
        return repository.save(c);
    }

    @Override
    public Card update(Card card, Integer id) {
        card.setId(id);
        Card c = repository.findById(id).get();
        card.setStatus(c.getStatus());
        return repository.save(card);
    }

    @Override
    public Card updateby(Card card) {
        return repository.save(card);
    }


    @Override
    public Page<Card> getByPage(int pageNumber, int maxRecord, String classify, String value, String status, HttpSession session) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        if (classify.equals("all") && value.equals("all")) {
            Page<Card> pagee = repository.findByStatus(Status.valueOf(status), pageable);
            session.setAttribute("quantitycardd", "Còn tất cả "+pagee.getTotalElements()+" thẻ!");
            return pagee;
        }
        else if (!classify.equals("all") && value.equals("all")) {
            Page<Card> pagee = repository.findByCategories_ClassifyAndStatus(ClassifyCard.valueOf(classify),Status.valueOf(status), pageable);

            if(pagee.getTotalElements()==0){
                session.setAttribute("quantitycardd", "Thẻ loại này đã hết, hãy thêm mới ngay");
            }else{
            session.setAttribute("quantitycardd", "Còn tất cả "+pagee.getTotalElements()+" thẻ loại "+classify);}
            return pagee;
        }
        else if (classify.equals("all") && !value.equals("all")) {

            Page<Card> pagee = repository.findByCategories_ValueAndStatus(ValueCard.valueOf(value),Status.valueOf(status), pageable);
            if(pagee.getTotalElements()==0){
                session.setAttribute("quantitycardd", "Thẻ mệnh giá này đã hết, hãy thêm mới ngay");
            }else{
                session.setAttribute("quantitycardd", "Còn tất cả "+pagee.getTotalElements()+" thẻ mệnh giá "+value);}
            return pagee;
        } else if (!classify.equals("all") && !value.equals("all")) {
            Page<Card> pagee = repository.findByCategories_ClassifyAndCategories_ValueAndStatus(ClassifyCard.valueOf(classify), ValueCard.valueOf(value),Status.valueOf(status), pageable);
            if(pagee.getTotalElements()==0){
                session.setAttribute("quantitycardd", "Thẻ "+classify+" "+value+" đã hết, hãy thêm mới ngay");
            }else{
                session.setAttribute("quantitycardd", "Còn tất cả "+pagee.getTotalElements()+" thẻ!! ");}
            return pagee;
        }
        return null;

    }
}
