package com.example.assm_sof3021.service;


import com.example.assm_sof3021.modal.Categories;
import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICategorieService {
    public List<Categories> getAll();
    public Categories add(Categories cate);
    public Optional<Categories> findbyid(Integer id);
    public void delete(Integer id);
    public Categories update(Categories cate, Integer id);


    public List<Categories> findByClassify(ClassifyCard classify);
    public List<Categories> findByValue(ValueCard value);

    public List<Categories> findByClassifyAndValue(ClassifyCard classify,ValueCard value);



//    ------mượn của thầy------
    Page<Categories> getByPage(int pageNumber, int maxRecord,String classify,String value);
}
