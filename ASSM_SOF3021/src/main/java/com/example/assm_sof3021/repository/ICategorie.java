package com.example.assm_sof3021.repository;

import com.example.assm_sof3021.modal.Categories;
import com.example.assm_sof3021.modal.Enum.ClassifyCard;
import com.example.assm_sof3021.modal.Enum.ValueCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategorie extends JpaRepository<Categories,Integer> {
 List<Categories> findByClassify(ClassifyCard classify);
 List<Categories> findByValue(ValueCard value);
 List<Categories> findByClassifyAndValue(ClassifyCard classify,ValueCard value);
 Page<Categories> findByClassify(ClassifyCard classify, Pageable pageable);
 Page<Categories> findByValue(ValueCard value, Pageable pageable);
 Page<Categories> findByClassifyAndValue(ClassifyCard classify,ValueCard value, Pageable pageable);
}
