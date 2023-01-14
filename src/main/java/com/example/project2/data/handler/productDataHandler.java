package com.example.project2.data.handler;

import com.example.project2.data.entity.productEntity;

public interface productDataHandler {

    productEntity saveProductEntity(Long Id, String name, int price, int stock);

    productEntity getProductEntity(Long Id);
}
