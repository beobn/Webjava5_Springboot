package com.example.assm_sof3021.modal;


import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import lombok.Data;


import javax.persistence.*;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
@Data
@Entity

public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    private ValueCard value;
    private ClassifyCard classify;
	private Date newDate;
    private Status status;

    @OneToMany(mappedBy = "categories")
    private List<Cart> carts;

    @OneToMany(mappedBy = "categories")
    private List<Card> cards;



}
