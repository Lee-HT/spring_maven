package com.example.project2.data.repository;

import com.example.project2.data.entity.productEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface productRepository extends JpaRepository<productEntity, String> {
}
