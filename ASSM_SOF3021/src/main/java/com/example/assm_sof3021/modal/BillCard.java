package com.example.assm_sof3021.modal;

import lombok.Data;

import javax.persistence.*;


@Entity @Data
public class BillCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Cart carts;
    @ManyToOne
    @JoinColumn(name = "id_card", referencedColumnName = "id")
    private Card cards;
}
