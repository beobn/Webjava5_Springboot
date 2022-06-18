package com.example.assm_sof3021.modal;


import com.example.assm_sof3021.modal.Enum.Status;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;
import java.util.List;



@Entity @Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String seri;
    private String number;
    private Date expiry ;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "id_categories", referencedColumnName = "id")
    private Categories categories;
    @OneToMany(mappedBy = "cards")
    private List<BillCard> billCards;
}
