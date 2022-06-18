package com.example.assm_sof3021.controller;

import com.example.assm_sof3021.modal.BillCard;
import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.service.impl.BillcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/billcard")
public class BillcardController {
    @Autowired
    BillcardService billcardService;
    @RequestMapping("/getall")
    public String getall(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "tim", defaultValue = "") String tim,
            Model model

    ){
        Page<BillCard> page = billcardService.getByPage(pageNumber, 10,tim);
        model.addAttribute("page", page);

        return "admin/billcard/getall";
    }
}
