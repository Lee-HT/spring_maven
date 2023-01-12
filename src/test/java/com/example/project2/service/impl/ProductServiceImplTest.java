package com.example.project2.service.impl;

import com.example.project2.data.dto.productDTO;
import com.example.project2.data.entity.productEntity;
import com.example.project2.data.handler.impl.productDataHandlerImpl;
import com.example.project2.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

//@SpringBootTest(classes = {productDataHandlerImpl.class, ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
@Import({productDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceImplTest {

    @MockBean
    productDataHandlerImpl productDataHandler;

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void getProductTest(){
        //given
        Mockito.when(productDataHandler.getProductEntity(1L))
                .thenReturn(new productEntity(1L,"pen",2000,3000));

        productDTO productdto = productService.getProduct(1L);

        Assertions.assertEquals(productdto.getProductID(),1L);
        Assertions.assertEquals(productdto.getProductName(),"pen");
        Assertions.assertEquals(productdto.getProductPrice(),2000);
        Assertions.assertEquals(productdto.getProductStock(),3000);

        verify(productDataHandler).getProductEntity(1L);
    }

    @Test
    public void setProductTest(){
        Mockito.when(productDataHandler.saveProductEntity(1L,"pen",2000,3000))
                .thenReturn(new productEntity(1L,"pen",2000,3000));

        productDTO productdto = productService.saveProduct(1L,"pen",2000,3000);

        Assertions.assertEquals(productdto.getProductID(),1L);
        Assertions.assertEquals(productdto.getProductName(),"pen");
        Assertions.assertEquals(productdto.getProductPrice(),2000);
        Assertions.assertEquals(productdto.getProductStock(),3000);

        verify(productDataHandler).saveProductEntity(1L,"pen",2000,3000);
    }
}
