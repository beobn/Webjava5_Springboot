package com.example.assm_sof3021.service.impl;
import com.example.assm_sof3021.modal.Enum.RoleUsers;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Users;
import com.example.assm_sof3021.repository.IUsers;
import com.example.assm_sof3021.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    IUsers repository;

    @Override
    public List<Users> getAll(Status status) {
        return repository.findByStatus(status);
    }
    @Override
    public Users add(Users users) {
        users.setId(null);
        users.setStatus(Status.STT0);
        users.setCreatDate(new Date());
        return repository.save(users);
    }

    @Override
    public Users delete(Integer id) {
        Users us = repository.findById(id).get();
        us.setStatus(Status.STT1);
        return repository.save(us);

    }

    @Override
    public Users update(Users user, Integer id) {
        user.setId(id);
        Users us = repository.findById(id).get();
        user.setStatus(us.getStatus());
        user.setCreatDate(us.getCreatDate());
        return repository.save(user);
    }

    @Override
    public Users findtk(String tk) {
        List<Users> listtk = new ArrayList<>();
        Users us= null;
        listtk =repository.findByUserName(tk);
        if(listtk.size()==0){
            listtk=repository.findByEmail(tk);
            if(listtk.size()==0){
                return us;
            }
            else{
                us=listtk.get(0);
                return us;
            }
        }else
        {
            us=listtk.get(0);
            return us;
        }



    }

    @Override
    public Page<Users> getByPage(int pageNumber, int maxRecord, String usName, String status, String role) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        if(usName.equals("all")&&role.equals("all")){
            Page<Users> page = repository.findByStatus(Status.valueOf(status), pageable);

            return page;
        }
        else if(!usName.equals("all")&&role.equals("all")){
            Page<Users> page = repository.findByUserNameContainingAndStatus(usName,Status.valueOf(status),pageable);
            return page;
        }
        else if(usName.equals("all")&&!role.equals("all")){
            Page<Users> page = repository.findByStatusAndRole(Status.valueOf(status), RoleUsers.valueOf(role),pageable);
            return page;
        }
        else if(!usName.equals("all")&&!role.equals("all")){
            Page<Users> page = repository.findByUserNameContainingAndStatusAndRole(usName,Status.valueOf(status),RoleUsers.valueOf(role),pageable);
            return page;
        }
        return null;
    }
}
