package com.example.project2.data.dao;

import com.example.project2.data.entity.productEntity;

public interface productDAO {

    productEntity saveProduct(productEntity productentity);

    productEntity getProduct(String productId);

}
