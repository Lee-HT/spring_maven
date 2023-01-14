package com.example.project2.service.Impl;

import com.example.project2.data.dto.productDTO;
import com.example.project2.data.entity.productEntity;
import com.example.project2.data.handler.productDataHandler;
import com.example.project2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;

@Service
public class ProductServiceImpl implements ProductService {
    productDataHandler productdataHandler;

    @Autowired
    public ProductServiceImpl(productDataHandler productdataHandler){
        this.productdataHandler = productdataHandler;
    }

    @Override
    public productDTO saveProduct(Long Id, String name, int price, int stock) {
        productEntity productentity = productdataHandler.saveProductEntity(Id, name, price, stock);

        productDTO productdto = new productDTO(productentity.getId(),
                productentity.getName(), productentity.getPrice(), productentity.getStock());

        return productdto;
    }

    @Override
    public productDTO getProduct(Long Id) {
        productEntity productentity = productdataHandler.getProductEntity(Id);

        productDTO productdto = new productDTO(productentity.getId(),
                productentity.getName(), productentity.getPrice(), productentity.getStock());

        return productdto;
    }
}
