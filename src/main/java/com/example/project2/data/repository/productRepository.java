package com.example.project2.data.repository;

import com.example.project2.data.entity.productEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<productEntity, String> {
}
