package com.example.project2.controller;


import com.example.project2.data.dto.productDTO;
import com.example.project2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/get/{productId}")
    public productDTO getProduct(@PathVariable String productId){
        return productService.getProduct(productId);
    }

    @PostMapping("/post")
    public productDTO createProduct(@RequestBody productDTO productdto){
        String productID = productdto.getProductID();
        String productName = productdto.getProductName();
        int productPrice = productdto.getProductPrice();
        int productStock = productdto.getProductStock();

        return productService.saveProduct(productID, productName, productPrice, productStock);
    }

    @DeleteMapping("/delete/{productId}")
    public productDTO deleteProduct(@PathVariable String productId) { return null; }

}
