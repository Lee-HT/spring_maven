package com.example.project2.service;

import com.example.project2.data.dto.productDTO;

public interface ProductService {

    productDTO saveProduct(Long Id,String name, int price, int stock);

    productDTO getProduct(Long Id);
}
