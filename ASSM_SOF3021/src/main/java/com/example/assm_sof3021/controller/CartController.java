package com.example.assm_sof3021.controller;

import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @RequestMapping("/getall")
    public String getall(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "idOrder", defaultValue = "") String idOrder,
            @RequestParam(name = "status", defaultValue = "", required = false) String status,
            Model model){
        Page<Cart> page = cartService.getByPage(pageNumber, 10,status,idOrder);
        List<Status> liststt = List.of(Status.values());
        model.addAttribute("page", page);
        model.addAttribute("liststt", liststt);
        return "admin/order/getall";
    }
}
