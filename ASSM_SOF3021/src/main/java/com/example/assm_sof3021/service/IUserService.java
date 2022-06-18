package com.example.assm_sof3021.service;

import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Users;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    public List<Users> getAll(Status status);
    public Users add(Users Users);
    public Users delete(Integer id);
    public Users update(Users Users, Integer id);
    public Users findtk(String tk);




    //    ------mượn của thầy------
    Page<Users> getByPage(int pageNumber, int maxRecord, String usName, String status,String role);


}
