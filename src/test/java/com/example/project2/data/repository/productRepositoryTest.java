package com.example.project2.data.repository;

import com.example.project2.data.entity.productEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class productRepositoryTest {

    @Autowired
    productRepository productrepository;

    @BeforeEach
    void GenerateData(){
        int count = 1;
        System.out.println("test");
        productrepository.save(getProduct(Long.valueOf(count),count++,2000,3000));
        productrepository.save(getProduct(Long.valueOf(count),count++,3000,4000));
        productrepository.save(getProduct(Long.valueOf(--count),count = count + 2,1500,400));
        productrepository.save(getProduct(Long.valueOf(count),count++,400,2300));
        productrepository.save(getProduct(Long.valueOf(count),count++,1200,3200));
        productrepository.save(getProduct(Long.valueOf(count),count++,1500,2200));
        productrepository.save(getProduct(Long.valueOf(count),count++,2200,800));
        productrepository.save(getProduct(Long.valueOf(count),count,3500,2500));

    }

    private productEntity getProduct(Long id, int nameNumber, int price, int stock){
        return new productEntity(id, "상품" + nameNumber, price, stock);
    }

    @Test
    void findTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundEntities = productrepository.findByproductName("상품4");
        for(productEntity productentity : foundEntities){
            System.out.println(productentity.toString());
        }

        List<productEntity> queryEntities = productrepository.queryByproductName("상품4");
        for(productEntity productentity : queryEntities){
            System.out.println(productentity.toString());
        }
    }

    @Test
    void existTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        System.out.println(productrepository.existsByproductName("상품4"));
    }

    @Test
    void countTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println("---- Test Data ----");
        }
        System.out.println("---- Test Data");

        System.out.println(productrepository.countByproductName("상품4"));
    }

    @Test
    @Transactional
    void deleteTest(){
        System.out.println("before : " + productrepository.count());

        productrepository.deleteByproductID(1L);
        productrepository.removeByproductID(9L);

        System.out.print("After : " + productrepository.count());
    }

}
