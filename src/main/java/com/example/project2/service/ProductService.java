package com.example.project2.service;

import com.example.project2.data.dto.productDTO;

public interface ProductService {

    productDTO saveProduct(String productId,String productName, int productPrice, int productStock);

    productDTO getProduct(String productId);
}
