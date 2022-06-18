package com.example.assm_sof3021.modal;


import com.example.assm_sof3021.modal.Enum.RoleUsers;
import com.example.assm_sof3021.modal.Enum.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String passWord;
    private String email;
    private String sdt;
    private Date creatDate;
    private RoleUsers role;
    private Status status;
    @OneToMany(mappedBy = "users")
    private List<Cart> carts;


}
