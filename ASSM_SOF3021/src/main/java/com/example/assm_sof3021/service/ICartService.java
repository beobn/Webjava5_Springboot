package com.example.assm_sof3021.service;


import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Users;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface ICartService {
    public List<Cart> fillall();
    public List<Cart> getall(Status status);
    public Cart add(Cart cart);
    public Cart update(Integer idOrder);
    public List<Cart> findByidOder(Integer idOder);
    public void delete(Integer id);

    Page<Cart> getByPage(int pageNumber, int maxRecord, String status,String idOrder);
}
