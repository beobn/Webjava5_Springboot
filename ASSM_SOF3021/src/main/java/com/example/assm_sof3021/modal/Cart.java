package com.example.assm_sof3021.modal;


import com.example.assm_sof3021.modal.Enum.Status;
import lombok.Data;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private String emailSend;
    private Integer idOrder;
    private Status status;
    private BigDecimal priceOrder;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Categories categories;

    @OneToMany(mappedBy = "carts")
    private List<BillCard> billCards;


}
