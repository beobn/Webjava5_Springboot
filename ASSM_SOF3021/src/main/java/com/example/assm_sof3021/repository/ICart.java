package com.example.assm_sof3021.repository;

import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.modal.Enum.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICart extends JpaRepository<Cart,Integer> {
    List<Cart> findByStatus(Status status);
    List<Cart> findByIdOrder(Integer idOrder);
    Page<Cart> findByStatus(Status status, Pageable pageable);
    Page<Cart> findByIdOrder(Integer idOrder,Pageable pageable);
    Page<Cart> findByIdOrderAndStatus(Integer idOrder,Status status,Pageable pageable);
}
