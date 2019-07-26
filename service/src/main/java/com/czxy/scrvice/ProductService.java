package com.czxy.scrvice;

import com.czxy.domain.Product;

import java.util.List;

public interface ProductService {


    Product findById(Integer id);

    //展示
    List<Product> findAll();
}
