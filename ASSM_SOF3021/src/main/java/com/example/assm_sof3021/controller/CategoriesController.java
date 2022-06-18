package com.example.assm_sof3021.controller;

import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.modal.Categories;
import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import com.example.assm_sof3021.service.impl.CardService;
import com.example.assm_sof3021.service.impl.CartService;
import com.example.assm_sof3021.service.impl.CategorieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/categori")
public class CategoriesController {
    @Autowired
    private CategorieService serviceCate;
    private CardService serviceCard;
    private CartService serviceCart;

    @RequestMapping("/getall")
    public String getAll(@RequestParam(name = "classify", defaultValue = "all", required = false) String classify, @RequestParam(name = "value", defaultValue = "all", required = false) String value, Model model, @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {

            List<ValueCard> listvalue = List.of(ValueCard.values());
            List<ClassifyCard> listclassify = List.of(ClassifyCard.values());
            model.addAttribute("listvalue", listvalue);
            model.addAttribute("listclassify", listclassify);
            Page<Categories> Catepage = serviceCate
                    .getByPage(pageNumber, 10,classify,value);
            model.addAttribute("page", Catepage);

        return "admin/categorie/getall";

    }

    @RequestMapping("/add")
    public String add(Model model, Categories cate, HttpSession session) {
        try {
            if (serviceCate.findByClassifyAndValue(cate.getClassify(),cate.getValue()).size()==0){
                Categories categorie = serviceCate.add(cate);
                session.setAttribute("success", "Thêm Mới Thành Công");
            }else{
                session.setAttribute("error", "Loại Thẻ Này Đã Có Không Thể Thêm");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Thêm Mới Thất Bại");
        }


        return "redirect:/admin/categori/getall";

    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id, HttpSession session) {
        try {
            serviceCate.delete(id);
            session.setAttribute("success", "Xóa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
        }
        return "redirect:/admin/categori/getall";
    }

    @RequestMapping("/update")
    public String update(Model model, Categories cateupdate, HttpSession session, @RequestParam Integer id) {
        try {
            Categories cate =
                    serviceCate.update(cateupdate, id);
            session.setAttribute("success", "Cập Nhật Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Cập Nhật Thất Bại");
        }

        return "redirect:/admin/categori/getall";
    }

    @RequestMapping("/deleteclick")
    public String deletetick(@RequestParam("cbdelete") Integer[] idcb, HttpSession session) {
        try {
            List<Categories> listct = serviceCate.getAll();
            for (int i = 0; i < listct.size(); i++) {
                for (int y = 0; y < idcb.length; y++) {
                    if (listct.get(i).getId() == idcb[y]) {
                        serviceCate.delete(listct.get(i).getId());
                    }
                }

            }
            System.out.println(idcb.length);
            session.setAttribute("success", "Xóa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
        }

        return "redirect:/admin/categori/getall";

    }



}
