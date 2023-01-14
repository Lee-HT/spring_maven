package com.example.project2.data.repository;

import com.example.project2.data.entity.productEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

        List<productEntity> foundEntities = productrepository.findByName("상품4");
        for (productEntity productentity : foundEntities) {
            System.out.println(productentity.toString());
        }

        List<productEntity> queryEntities = productrepository.queryByName("상품4");
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

        System.out.println(productrepository.existsByName("상품4"));
        System.out.println(productrepository.existsByName("상품2"));
    }

    @Test
    void countTest() {
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for (productEntity productentity : foundAll) {
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data");

        System.out.println(productrepository.countByName("상품4"));
    }

    @Test
    @Transactional
    void deleteTest() {
        System.out.println("Before : " + productrepository.count());

        productrepository.deleteById(1L);
        productrepository.removeById(9L);

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

        List<productEntity> foundEntities = productrepository.findFirst5ByName("상품123");
        for (productEntity productentity : foundEntities) {
            System.out.println(productentity.toString());
        }

        List<productEntity> foundEntities2 = productrepository.findTop3ByName("상품123");
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

        System.out.println(productrepository.findByIdIs(1L));
        System.out.println(productrepository.findByIdEquals(1L));
    }

    @Test
    void notTest() {
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }

        List<productEntity> foundEntitites = productrepository.findByIdNot(1L);
        for(productEntity productentity : foundEntitites){
            System.out.println(productentity);
        }

        System.out.println(productrepository.findByIdIsNot(1L));
    }

    @Test
    void andTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        System.out.println(productrepository.findTopByIdAndName(2L,"상품4"));
    }

    @Test
    void greaterTest() {
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> productEntities = productrepository.findByPriceGreaterThan(5000);

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

        System.out.println(productrepository.findByNameContaining("상품1"));

    }

    @Test
    public void basicCRUDTest(){
        // create
        // given
        productEntity productentity = productEntity.builder()
                .id(1000L)
                .name("testP")
                .price(1000)
                .stock(500)
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

        List<productEntity> foundProducts = productrepository.findByNameContainingOrderByStockAsc("상품");
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }

        foundProducts = productrepository.findByNameContainingOrderByStockDesc("상품");
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }

        foundProducts = productrepository.findByNameContainingOrderByPriceAscStockDesc("상품");
        for(productEntity productentity : foundProducts) {
            System.out.println(productentity);
        }
    }

    @Test
    void multiOrderByTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");

        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByNameContainingOrderByPriceAscStockDesc("상품");
        for(productEntity productentity : foundProducts){
            System.out.println(productentity.toString());
        }
    }

    @Test
    void OrderByWithParameterTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByNameContaining(
                "상품", Sort.by(Order.asc("price")));
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }

        foundProducts = productrepository.findByNameContaining("상품",
                Sort.by(Order.asc("price"),Order.desc("price")));
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

        List<productEntity> foundProducts = productrepository.findByPriceGreaterThan(200,
                PageRequest.of(0,3));
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }

        foundProducts = productrepository.findByPriceGreaterThan(200, PageRequest.of(4,2));
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }
    }

    @Test
    public void queryTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByPriceBasis();
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }
    }

    @Test
    public void nativeQueryTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByPriceBasisNativeQuery();
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }
    }

    @Test
    public void parameterQueryTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByPriceWithParameter(2000);
        for(productEntity productentity : foundProducts){
            System.out.println(productentity.toString());
        }
    }

    @Test
    public void parameterNamingQueryTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByPriceWithParameterNaming(2000);
        for(productEntity productentity : foundProducts){
            System.out.println(productentity.toString());
        }
    }

    @Test
    public void parameterNamingQueryTest2(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll){
            System.out.print(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByPriceWithParameterNaming2(2000);
        for(productEntity productentity : foundProducts){
            System.out.println(productentity.toString());
        }
    }

    @Test
    public void nativeQueryPagingTest(){
        List<productEntity> foundAll = productrepository.findAll();
        System.out.println("---- Test Data ----");
        for(productEntity productentity : foundAll) {
            System.out.println(productentity.toString());
        }
        System.out.println("---- Test Data ----");

        List<productEntity> foundProducts = productrepository.findByPriceWithParameterPaging(2000,
                PageRequest.of(2,2));
        for(productEntity productentity : foundProducts){
            System.out.println(productentity);
        }

    }
}