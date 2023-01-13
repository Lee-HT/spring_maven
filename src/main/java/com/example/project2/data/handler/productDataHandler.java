package com.example.project2.data.handler;

import com.example.project2.data.entity.productEntity;

public interface productDataHandler {

    productEntity saveProductEntity(Long ProductID, String ProductName, int ProductPrice, int ProductStock);

    productEntity getProductEntity(Long ProductID);
}
