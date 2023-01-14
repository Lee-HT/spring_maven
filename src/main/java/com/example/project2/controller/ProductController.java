package com.example.project2.controller;


import com.example.project2.common.Constants;
import com.example.project2.common.Exception.IAException;
import com.example.project2.data.dto.productDTO;
import com.example.project2.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //logger로 성능 체크
    @GetMapping("/get/{productId}")
    public productDTO getProduct(@PathVariable Long productId){
        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} of IA API", "getProduct");

        productDTO productdto = productService.getProduct(productId);

        LOGGER.info("[ProductController] Response :: productID = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms"
                ,productdto.getId(),productdto.getName(),productdto.getPrice(),productdto.getStock()
                ,(System.currentTimeMillis() - startTime));
        return productdto;
    }

    @PostMapping(value = "/post")
    public ResponseEntity<productDTO> createProduct(@Valid @RequestBody productDTO productdto){

        //Validatioin Example (String)
//        if (productdto.getProductID().equals("") || productdto.getProductID().isEmpty()){  //#테스트
//            LOGGER.error("[createProduct] failed Response :: productID is Empty");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productdto);
//        }

        Long productID = productdto.getId();
        String productName = productdto.getName();
        int productPrice = productdto.getPrice();
        int productStock = productdto.getStock();

        productDTO response = productService.saveProduct(productID,productName,productPrice,productStock);

        LOGGER.info(
                "[createProduct] Response >> productID  : {}, productName : {}, productPrice : {}, productStock : {}"
                ,response.getId(), response.getName(), response.getStock(), response.getStock()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/delete/{productId}")
    public productDTO deleteProduct(@PathVariable Long productId) {
        return null;
    }

    @PostMapping("/post/exception")
    public void exceptionTest() throws IAException {
        throw new IAException(Constants.ExceptionClass.PRODUCT, HttpStatus.NOT_FOUND, "의도한 에러 발생");
    }

}
