package com.example.project2.data.repository;

import com.example.project2.data.entity.productEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@SpringBootTest
public class productRepositoryTest {

    @Autowired
    productRepository productrepository;

    @BeforeEach
    void GenerateData() {
        int count = 1;
        System.out.println("test");
        productrepository.save(getProduct(Long.valueOf(count), count++, 2000, 3000));
        productrepository.save(getProduct(Long.valueOf(count), count++, 3000, 4000));
        productrepository.save(getProduct(Long.valueOf(--count), count = count + 2, 1500, 400));
        productrepository.save(getProduct(Long.valueOf(count), count++, 400, 2300));
        productrepository.save(getProduct(Long.valueOf(count), count++, 1200, 3200));
        productrepository.save(getProduct(Long.valueOf(count), count++, 1500, 2200));
        productrepository.save(getProduct(Long.valueOf(count), count++, 3500, 2500));
        productrepository.save(getProduct(Long.valueOf(count), count++, 6200, 800));
        productrepository.save(getProduct(Long.valueOf(count), count++, 5800, 1500));
        productrepository.save(getProduct(Long.valueOf(count), count, 2000, 5200));

    }

    private productEntity getProduct(Long id, int nameNumber, int price, int stock) {
        return new productEntity(id, "상품" + nameNumber, price, stock);
    }

    @Test
    void findTest() {
        List<productEntity> foundAll = productrepository.findAll();

        System.out.println("---- Test Data ----");
        for (productEntity productentity : foundAll) {
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundEntities = productrepository.findByproductName("상품4");
        for (productEntity productentity : foundEntities) {
            System.out.println(productentity.toString());
        }

        List<productEntity> queryEntities = productrepository.queryByproductName("상품4");
        for (productEntity productentity : queryEntities) {
            System.out.println(productentity.toString());
        }
    }

    @Test
    void existTest() {
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for (productEntity productentity : foundAll) {
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        System.out.println(productrepository.existsByproductName("상품4"));
        System.out.println(productrepository.existsByproductName("상품2"));
    }

    @Test
    void countTest() {
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for (productEntity productentity : foundAll) {
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data");

        System.out.println(productrepository.countByproductName("상품4"));
    }

    @Test
    @Transactional
    void deleteTest() {
        System.out.println("Before : " + productrepository.count());

        productrepository.deleteByproductID(1L);
        productrepository.removeByproductID(9L);

        System.out.print("After : " + productrepository.count());
    }

    @Test
    void topTest() {
        productrepository.save(getProduct(100L, 123, 1500, 5000));
        productrepository.save(getProduct(101L, 123, 2500, 5000));
        productrepository.save(getProduct(102L, 123, 800, 5000));
        productrepository.save(getProduct(103L, 123, 1800, 5000));
        productrepository.save(getProduct(104L, 123, 2000, 5000));
        productrepository.save(getProduct(105L, 123, 700, 5000));
        productrepository.save(getProduct(106L, 123, 2500, 5000));
        productrepository.save(getProduct(107L, 123, 2700, 5000));

        List<productEntity> foundEntities = productrepository.findFirst5ByproductName("상품123");
        for (productEntity productentity : foundEntities) {
            System.out.println(productentity.toString());
        }

        List<productEntity> foundEntities2 = productrepository.findTop3ByproductName("상품123");
        for (productEntity productentity : foundEntities2) {
            System.out.println(productentity.toString());
        }
    }

    /* 조건자 키워드 테스트 */
    @Test
    void isEqualsTest() {
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for (productEntity productentity : foundAll) {
            System.out.println(productentity.toString());
        }

        System.out.println("---- Test Data ----");

        System.out.println(productrepository.findByproductIDIs(1L));
        System.out.println(productrepository.findByproductIDEquals(1L));
    }

    @Test
    void notTest() {
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }

        List<productEntity> foundEntitites = productrepository.findByproductIDNot(1L);
        for(productEntity productentity : foundEntitites){
            System.out.println(productentity);
        }

//        System.out.println(productrepository.findByproductIDIsNot(1L));
    }

    @Test
    void andTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

//        System.out.println(productrepository.findTopByproductIDAndproductName(1L,"상품1"));
    }

    @Test
    void greaterTest() {
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> productEntities = productrepository.findByproductPriceGreaterThan(5000);

        for(productEntity productentity : productEntities){
            System.out.println(productentity);
        }
    }

    @Test
    void containTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        System.out.println(productrepository.findByproductNameContaining("상품1"));

    }

    @Test
    public void basicCRUDTest(){
        // create
        // given
        productEntity productentity = productEntity.builder()
                .productID(1000L)
                .productName("testP")
                .productPrice(1000)
                .productStock(500)
                .build();

    }

    @Test
    void orderByTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

//        List<productEntity> foundProducts = productrepository.findByproductNameContainingOrderByproductStockAsc("상품");
//        for(productEntity productentity : foundProducts){
//            System.out.println(productentity);
//        }

//        foundProducts = productrepository.findByproductNameContainingOrderByproductStockDesc("상품");
//        for(productEntity productentity : foundProducts){
//            System.out.println(productentity);
//        }

//        foundProducts = productrepository.findByproductNameContainingOrderByproductPriceAscproductStockDesc("상품");
//        for(productEntity productentity : foundProducts){
//            System.out.println(productentity);
    }

    @Test
    void multiOrderByTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

//        List<productEntity> foundProducts = productrepository.findByproductNameContainingOrderByproductPriceAscproductStockDesc(상품);
//        for(productEntity productentity : foundProducts){
//            System.out.println(productentity.toString());
//        }
    }

    @Test
    void OrderByWithParameterTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByproductNameContaining(
                "상품", Sort.by(Order.asc("productPrice")));
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }

        foundProducts = productrepository.findByproductNameContaining("상품",
                Sort.by(Order.asc("productPrice"),Order.desc("productStock")));
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }

    }

    @Test
    void pagingTest() {
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByproductPriceGreaterThan(200,
                PageRequest.of(0,3));
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }

        foundProducts = productrepository.findByproductPriceGreaterThan(200, PageRequest.of(4,2));
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }
    }

}