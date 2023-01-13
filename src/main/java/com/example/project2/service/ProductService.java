package com.example.project2.service;

import com.example.project2.data.dto.productDTO;

public interface ProductService {

    productDTO saveProduct(Long ProductId,String ProductName, int ProductPrice, int ProductStock);

    productDTO getProduct(Long ProductId);
}
