package com.example.assm_sof3021.service.impl;

import com.example.assm_sof3021.modal.Categories;
import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import com.example.assm_sof3021.repository.ICategorie;
import com.example.assm_sof3021.service.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class CategorieService implements ICategorieService {
    @Autowired
    ICategorie repository;

    @Override
    public List<Categories> getAll() {
        return repository.findAll();
    }

    @Override
    public Categories add(Categories cate) {
        cate.setId(null);
        cate.setNewDate(new Date());
        cate.setStatus(Status.STT0);
        return repository.save(cate);
    }

    @Override
    public Optional<Categories> findbyid(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    @Override
    public Categories update(Categories cate, Integer id) {
        cate.setId(id);
        Categories c = repository.findById(id).get();
        cate.setNewDate(c.getNewDate());
        cate.setStatus(Status.STT0);
        return repository.save(cate);
    }



    @Override
    public List<Categories> findByClassify(ClassifyCard classify) {

        return repository.findByClassify(classify);
    }

    @Override
    public List<Categories> findByValue(ValueCard value) {
        return repository.findByValue(value);
    }

    @Override
    public List<Categories> findByClassifyAndValue(ClassifyCard classify, ValueCard value) {
        return repository.findByClassifyAndValue(classify, value);
    }


    //    ------mượn của thầy------
    @Override
    public Page<Categories> getByPage(int pageNumber, int maxRecord, String classify, String value) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        if (classify.equals("all") && value.equals("all")) {
            Page<Categories> pagee = repository.findAll(pageable);
            return pagee;
        } else if (!classify.equals("all") && value.equals("all")) {
            Page<Categories> pagee = repository.findByClassify(ClassifyCard.valueOf(classify), pageable);
            return pagee;
        } else if (classify.equals("all") && !value.equals("all")) {
            Page<Categories> pagee = repository.findByValue(ValueCard.valueOf(value), pageable);
            return pagee;
        } else if (!classify.equals("all") && !value.equals("all")) {
            Page<Categories> pagee = repository.findByClassifyAndValue(ClassifyCard.valueOf(classify), ValueCard.valueOf(value), pageable);
            return pagee;
        }

        return null;
    }
}
