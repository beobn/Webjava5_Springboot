package com.example.assm_sof3021.repository;

import com.example.assm_sof3021.modal.BillCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IBillcard extends JpaRepository<BillCard,Integer> {
    List<BillCard> findByCarts_IdOrder(Integer idOder);
    Page<BillCard> findByCards_Seri(String seri, Pageable pageable);
    Page<BillCard> findByCarts_IdOrder(Integer idOder,Pageable pageable);
}
