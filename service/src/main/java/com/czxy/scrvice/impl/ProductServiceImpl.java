package com.czxy.scrvice.impl;

import com.czxy.dao.ProductMapper;
import com.czxy.domain.Product;
import com.czxy.scrvice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = productMapper.selectAll();
        return list;
    }
}
