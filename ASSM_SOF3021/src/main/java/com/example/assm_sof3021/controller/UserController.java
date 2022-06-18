package com.example.assm_sof3021.controller;

import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Enum.RoleUsers;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import com.example.assm_sof3021.modal.Users;
import com.example.assm_sof3021.service.impl.UserService;
import com.example.assm_sof3021.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService serviceUser;

    @RequestMapping("/getall")
    public String getAll(@RequestParam(name = "usName", defaultValue = "all", required = false) String usName,
                         @RequestParam(name = "role", defaultValue = "all", required = false) String role,
                         Model model, @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber
            , @RequestParam(name = "status", defaultValue = "STT0", required = false) String status
    ){
        List<RoleUsers> listrole = List.of(RoleUsers.values());
        model.addAttribute("listrole", listrole);
        Page<Users> uspage = serviceUser
                .getByPage(pageNumber, 10,usName,status,role);
        model.addAttribute("page", uspage);
        return "admin/user/getall";
    }
    @RequestMapping("/add")
    public String add(@RequestParam(name="pw") String pw, Model model, Users user, HttpSession session) {
        try {

            user.setPassWord(EncryptUtil.mhPass(pw));
            Users users = serviceUser.add(user);
            session.setAttribute("success", "Thêm Mới Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Thêm Mới Thất Bại");
        }


        return "redirect:/admin/user/getall";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id, HttpSession session) {
        try {
            serviceUser.delete(id);
            session.setAttribute("success", "Xóa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
        }
        return "redirect:/admin/user/getall";
    }
    @RequestMapping("/update")
    public String update(Model model, Users usupdate, HttpSession session, @RequestParam Integer id) {
        try {


            Users us =
                    serviceUser.update(usupdate, id);
            session.setAttribute("success", "Cập Nhật Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Cập Nhật Thất Bại");
        }

        return "redirect:/admin/user/getall";
    }
    @RequestMapping("/deleteclick")
    public String deletetick(@RequestParam("cbdelete") Integer[] idcb, HttpSession session) {
        try {
            List<Users> listcard = serviceUser.getAll(Status.STT0);
            for (int i = 0; i < listcard.size(); i++) {
                for (int y = 0; y < idcb.length; y++) {
                    if (listcard.get(i).getId() == idcb[y]) {
                        serviceUser.delete(listcard.get(i).getId());
                    }
                }

            }
            System.out.println(idcb.length);
            session.setAttribute("success", "Xóa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
        }

        return "redirect:/admin/user/getall";

    }

}
