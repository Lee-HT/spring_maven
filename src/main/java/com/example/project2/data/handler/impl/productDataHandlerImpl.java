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
    public productEntity saveProductEntity(String productID, String productName, int productPrice, int productStock){
        productEntity productentity = new productEntity(productID, productName, productPrice, productStock);

        return productdao.saveProduct(productentity);
    }

    public productEntity getProductEntity(String productId) {
        return productdao.getProduct(productId);
    }
}
