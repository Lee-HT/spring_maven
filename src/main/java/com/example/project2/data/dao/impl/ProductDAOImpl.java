package com.example.project2.data.dao.impl;

import com.example.project2.data.dao.productDAO;
import com.example.project2.data.entity.productEntity;
import com.example.project2.data.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAOImpl implements productDAO {
    productRepository productrepository;

    @Autowired
    public ProductDAOImpl(productRepository productrepository){
        this.productrepository = productrepository;
    }

    @Override
    public productEntity saveProduct(productEntity productentity) {
        productrepository.save(productentity);
        return productentity;
    }

    @Override
    public productEntity getProduct(String productId) {
        productEntity productentity = productrepository.getById(productId);
        return productentity;
    }
}
