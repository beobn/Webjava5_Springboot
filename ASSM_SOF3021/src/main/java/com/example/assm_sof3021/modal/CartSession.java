package com.example.assm_sof3021.modal;


import lombok.Data;

import java.math.BigDecimal;


@Data
public class CartSession {
    private Integer id;
    private Integer idCategori;
    private String name;
    private Integer idUser;
    private Integer quantity;
    private String emailSend;
    private Integer slcon;
    private BigDecimal price;




}
