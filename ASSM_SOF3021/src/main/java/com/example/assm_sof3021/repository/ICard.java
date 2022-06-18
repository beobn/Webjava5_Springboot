package com.example.assm_sof3021.repository;

import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Categories;
import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ICard extends JpaRepository<Card,Integer> {

    List<Card> findByStatus(Status status);
    List<Card> findByStatusAndCategories_Id(Status status,Integer idcate);
    List<Card> findByCategories_ClassifyAndCategories_ValueAndStatus(ClassifyCard classifyCard,ValueCard valueCard,Status status);
    Page<Card> findByStatus(Status status, Pageable pageable);
    Page<Card> findByCategories_ClassifyAndStatus(ClassifyCard classifyCard,Status status, Pageable pageable);
    Page<Card> findByCategories_ValueAndStatus(ValueCard valueCard,Status status, Pageable pageable);
    Page<Card> findByCategories_ClassifyAndCategories_ValueAndStatus(ClassifyCard classifyCard,ValueCard valueCard,Status status, Pageable pageable);

}
