package com.example.project2.data.handler;

import com.example.project2.data.entity.productEntity;

public interface productDataHandler {

    productEntity saveProductEntity(String productID, String productName, int productPrice, int productStock);

    productEntity getProductEntity(String productID);
}
