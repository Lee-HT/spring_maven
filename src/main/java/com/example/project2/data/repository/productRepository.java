package com.example.project2.data.repository;

import com.example.project2.data.entity.productEntity;
import io.swagger.models.auth.In;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface productRepository extends JpaRepository<productEntity, Long> {
    /* 쿼리 메소드의 주제 키워드 */

    //조회
    List<productEntity> findByName(String name);
    List<productEntity> queryByName(String name);

    //존재 유무
    boolean existsByName(String name);

    //쿼리 결과 개수
    long countByName(String name);

    //삭제
    void deleteById(Long id);
    long removeById(Long id);

    //값 개수 제한
    List<productEntity> findFirst5ByName(String name);
    List<productEntity> findTop3ByName(String name);

    /* 쿼리 메소드의 조건자 키워드 */

    // Is, Equals (생략 가능)
    // Logical Keyword : IS, Keyword Expressions : Is, Equals, (or no keyword)
    // findByNumber 메소드와 동일하게 동작
    productEntity findByIdIs(Long id);
    productEntity findByIdEquals(Long id);

    // (Is)Not
    List<productEntity> findByIdNot(Long id);
    List<productEntity> findByIdIsNot(Long id);

    // (Is)Null, (Is)Notnull
    List<productEntity> findByStockIsNull();
    List<productEntity> findByStockIsNotNull();

    // And, Or
    List<productEntity> findTopByIdAndName(Long id, String name);

    // (Is)GreaterThan. (Is)LessThan, (Is)Between
    List<productEntity> findByPriceGreaterThan(Integer price);

    // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith
    List<productEntity> findByNameContaining(String name);

    //정렬 , 페이징
    List<productEntity> findByNameContainingOrderByStockAsc(String name);
    List<productEntity> findByNameContainingOrderByStockDesc(String name);

    List<productEntity> findByNameContainingOrderByPriceAscStockDesc(String name);

    List<productEntity> findByNameContaining(String name, Sort sort);

    List<productEntity> findByPriceGreaterThan(Integer price, Pageable pageable);


    // QUERY
    @Query("SELECT p FROM productEntity p WHERE p.price > 2000")
    List<productEntity> findByPriceBasis();

    // nativeQuery는 JPQL이 아닌 SQL그대로
    // FROM product , p.product_price different
    @Query(value = "SELECT * FROM product p WHERE p.price > 2000", nativeQuery = true)
    List<productEntity> findByPriceBasisNativeQuery();

    @Query("SELECT p FROM productEntity p WHERE p.price > ?1")
    List<productEntity> findByPriceWithParameter(Integer price);

    @Query("SELECT p FROM productEntity p WHERE p.price > :price")
    List<productEntity> findByPriceWithParameterNaming(Integer price);

    @Query("SELECT p FROM productEntity p WHERE p.price > :pri ")
    List<productEntity> findByPriceWithParameterNaming2(@Param("pri") Integer price);

    @Query(value = "SELECT * FROM product WHERE price",
    countQuery = "SELECT count(*) FROM product WHERE price > ?1",
    nativeQuery = true)
    List<productEntity> findByPriceWithParameterPaging(Integer price, Pageable pageable);

}
