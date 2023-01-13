package com.example.project2.data.repository;

import com.example.project2.data.entity.productEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface productRepository extends JpaRepository<productEntity, Long> {
    /* 쿼리 메소드의 주제 키워드 */

    //조회
    List<productEntity> findByProductName(String name);
    List<productEntity> queryByProductName(String name);

    //존재 유무
    boolean existsByProductName(String name);

    //쿼리 결과 개수
    long countByProductName(String name);

    //삭제
    void deleteByProductID(Long id);
    long removeByProductID(Long id);

    //값 개수 제한
    List<productEntity> findFirst5ByProductName(String name);
    List<productEntity> findTop3ByProductName(String name);

    /* 쿼리 메소드의 조건자 키워드 */

    // Is, Equals (생략 가능)
    // Logical Keyword : IS, Keyword Expressions : Is, Equals, (or no keyword)
    // findByNumber 메소드와 동일하게 동작
    productEntity findByProductIDIs(Long id);
    productEntity findByProductIDEquals(Long id);

    // (Is)Not
    List<productEntity> findByProductIDNot(Long id);
    List<productEntity> findByProductIDIsNot(Long id);

    // (Is)Null, (Is)Notnull
    List<productEntity> findByProductStockIsNull();
    List<productEntity> findByProductStockIsNotNull();

    // And, Or
//    List<productEntity> findTopByProductIDAndProductName(Long id, String name); // product 대문자로..

    // (Is)GreaterThan. (Is)LessThan, (Is)Between
    List<productEntity> findByProductPriceGreaterThan(Integer price);

    // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith
    List<productEntity> findByProductNameContaining(String name);

    //정렬 , 페이징
//    List<productEntity> findByProductNameContainingOrderByProductStockAsc(String name);
//    List<productEntity> findByProductNameContainingOrderByProductStockDesc(String name);

//    List<productEntity> findByProductNameContainingOrderByProductPriceAscStockDesc(String name);

    List<productEntity> findByProductNameContaining(String name, Sort sort);

    List<productEntity> findByProductPriceGreaterThan(Integer price, Pageable pageable);


    // QUERY
    @Query("SELECT p FROM productEntity p WHERE p.ProductPrice > 2000")
    List<productEntity> findByPriceBasis();

    // nativeQuery는 JPQL이 아닌 SQL그대로
    // FROM product , p.product_price different
    @Query(value = "SELECT * FROM product p WHERE p.product_price > 2000", nativeQuery = true)
    List<productEntity> findByPriceBasisNativeQuery();

    @Query("SELECT p FROM productEntity p WHERE p.ProductPrice > ?1")
    List<productEntity> findByPriceWithParameter(Integer price);

    @Query("SELECT p FROM productEntity p WHERE p.ProductPrice > :price")
    List<productEntity> findByPriceWithParameterNaming(Integer price);

    @Query("SELECT p FROM productEntity p WHERE p.ProductPrice > :pri ")
    List<productEntity> findByPriceWithParameterNaming2(@Param("pri") Integer price);

    @Query(value = "SELECT * FROM product WHERE product_price",
    countQuery = "SELECT count(*) FROM product WHERE product_price > ?1",
    nativeQuery = true)
    List<productEntity> findByPriceWithParameterPaging(Integer price, Pageable pageable);

}
