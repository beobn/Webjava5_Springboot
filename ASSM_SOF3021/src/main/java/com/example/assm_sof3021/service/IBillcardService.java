package com.example.assm_sof3021.service;

import com.example.assm_sof3021.modal.BillCard;
import com.example.assm_sof3021.modal.Cart;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IBillcardService {

    public BillCard add(BillCard billCard);
    List<BillCard> finbyidorder(Integer id);

    Page<BillCard> getByPage(int pageNumber, int maxRecord,String tim);
}
