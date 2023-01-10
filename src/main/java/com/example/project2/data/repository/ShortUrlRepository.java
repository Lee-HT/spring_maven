package com.example.project2.data.repository;

import com.example.project2.data.entity.ShortUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Long> {
    ShortUrlEntity findByUrl(String url);

    ShortUrlEntity findByOrgUrl(String originalUrl);

}
