package com.example.assm_sof3021.repository;


import com.example.assm_sof3021.modal.Enum.RoleUsers;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUsers extends JpaRepository<Users,Integer> {
    List<Users> findByStatus(Status status);
    List<Users> findByUserName(String tk);
    List<Users> findByEmail(String tk);
    Page<Users> findByStatus( Status status, Pageable pageable);

    Page<Users> findByStatusAndRole( Status status,RoleUsers role, Pageable pageable);

    Page<Users> findByUserNameContainingAndStatus(String name, Status status, Pageable pageable);

    Page<Users> findByUserNameContainingAndStatusAndRole(String usName,  Status status,RoleUsers role, Pageable pageable);

}
