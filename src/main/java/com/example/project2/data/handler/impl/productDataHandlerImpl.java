package com.example.project2.data.handler.impl;

import com.example.project2.data.dao.productDAO;
import com.example.project2.data.entity.productEntity;
import com.example.project2.data.handler.productDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class productDataHandlerImpl implements productDataHandler {

    productDAO productdao;

    @Autowired
    public productDataHandlerImpl(productDAO productdao){
        this.productdao = productdao;
    }

    @Override
    public productEntity saveProductEntity(Long Id, String name, int price, int stock){
        productEntity productentity = new productEntity(Id, name, price, stock);

        return productdao.saveProduct(productentity);
    }

    @Override
    public productEntity getProductEntity(Long Id) {
        return productdao.getProduct(Id);
    }
}
