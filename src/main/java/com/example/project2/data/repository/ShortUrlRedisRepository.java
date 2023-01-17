package com.example.project2.data.repository;

import com.example.project2.data.dto.ShortUrlResponseDTO;
import org.springframework.data.repository.CrudRepository;

public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDTO, String> {
}
