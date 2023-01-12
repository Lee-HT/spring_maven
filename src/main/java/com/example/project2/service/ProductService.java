package com.example.project2.service;

import com.example.project2.data.dto.productDTO;

public interface ProductService {

    productDTO saveProduct(Long productId,String productName, int productPrice, int productStock);

    productDTO getProduct(Long productId);
}
