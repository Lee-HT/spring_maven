package com.example.project2.service.Impl;

import com.example.project2.data.dto.productDTO;
import com.example.project2.data.entity.productEntity;
import com.example.project2.data.handler.productDataHandler;
import com.example.project2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    productDataHandler productdataHandler;

    @Autowired
    public ProductServiceImpl(productDataHandler productdataHandler){
        this.productdataHandler = productdataHandler;
    }

    @Override
    public productDTO saveProduct(String productId, String productName, int productPrice, int productStock) {
        productEntity productentity = productdataHandler.saveProductEntity(productId, productName, productPrice, productStock);

        productDTO productdto = new productDTO(productentity.getProductID(),
                productentity.getProductName(), productentity.getProductPrice(), productentity.getProductStock());

        return productdto;
    }

    @Override
    public productDTO getProduct(String productId) {
        productEntity productentity = productdataHandler.getProductEntity(productId);

        productDTO productdto = new productDTO(productentity.getProductID(),
                productentity.getProductName(), productentity.getProductPrice(), productentity.getProductStock());

        return productdto;
    }
}
