package com.example.assm_sof3021.service.impl;


import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.repository.ICart;
import com.example.assm_sof3021.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    ICart repository;


    @Override
    public List<Cart> fillall() {
        return repository.findAll();
    }

    @Override
    public List<Cart> getall(Status status) {
        return repository.findByStatus(status);
    }



    @Override
    public Cart add(Cart cart) {
        cart.setId(null);
        cart.setStatus(Status.STT0);
        return repository.save(cart);
    }

    @Override
    public Cart update(Integer idOrder) {
        List<Cart> ud=
        repository.findByIdOrder(idOrder);
        ud.get(0).setStatus(Status.STT1);
        return repository.save(ud.get(0));
    }

    @Override
    public List<Cart> findByidOder(Integer idOder) {

        return repository.findByIdOrder(idOder);
    }

    @Override
    public void delete(Integer id) {
        List<Cart> c = repository.findByIdOrder(id);
        repository.deleteById(c.get(0).getId());
    }

    @Override
    public Page<Cart> getByPage(int pageNumber, int maxRecord, String status, String idOrder) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        if(status.equals("")&&idOrder.equals("")){
            Page<Cart> page = repository.findAll(pageable);
            return page;
        }else if(!status.equals("")&&idOrder.equals("")){
            Page<Cart> page = repository.findByStatus(Status.valueOf(status),pageable);
            return page;
        }else if(status.equals("")&&!idOrder.equals("")){
            Page<Cart> page = repository.findByIdOrder(Integer.parseInt(idOrder),pageable);
            return page;
        }else if(!status.equals("")&&!idOrder.equals("")){
            Page<Cart> page = repository.findByIdOrderAndStatus(Integer.parseInt(idOrder),Status.valueOf(status),pageable);
            return page;
        }

        return null;
    }
}
