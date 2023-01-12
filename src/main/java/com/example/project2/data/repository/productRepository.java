package com.example.project2.data.repository;

import com.example.project2.data.entity.productEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<productEntity, Long> {
    /* 쿼리 메소드의 주제 키워드 */

    //조회
    List<productEntity> findByproductName(String name);
    List<productEntity> queryByproductName(String name);

    //존재 유무
    boolean existsByproductName(String name);

    //쿼리 결과 개수
    long countByproductName(String name);

    //삭제
    void deleteByproductID(Long id);
    long removeByproductID(Long id);

    //값 개수 제한
    List<productEntity> findFirst5ByproductName(String name);
    List<productEntity> findTop3ByproductName(String name);

    /* 쿼리 메소드의 조건자 키워드 */

    // Is, Equals (생략 가능)
    // Logical Keyword : IS, Keyword Expressions : Is, Equals, (or no keyword)
    // findByNumber 메소드와 동일하게 동작
    productEntity findByproductIDIs(Long id);
    productEntity findByproductIDEquals(Long id);

    // (Is)Not
    List<productEntity> findByproductIDNot(Long id);
    List<productEntity> findByproductIDIsNot(Long id);

    // (Is)Null, (Is)Notnull
    List<productEntity> findByproductStockIsNull();
    List<productEntity> findByproductStockIsNotNull();

    // And, Or
//    List<productEntity> findTopByproductIDAndproductName(Long id, String name);

    // (Is)GreaterThan. (Is)LessThan, (Is)Between
    List<productEntity> findByproductPriceGreaterThan(Integer price);

    // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith
    List<productEntity> findByproductNameContaining(String name);


}
