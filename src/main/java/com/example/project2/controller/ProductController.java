package com.example.project2.controller;


import com.example.project2.data.dto.productDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService)

    @GetMapping("/{productId}")
    public productDTO getProduct(@PathVariable String productId){
        return productdto;
    }

    @DeleteMapping("/{productId}")
    public productDTO deleteProduct(@PathVariable String productId){
        return null;
    }

}
