package com.example.project2.service;

import com.example.project2.data.dto.ShortUrlResponseDTO;

public interface ShortUrlService {
    ShortUrlResponseDTO getShortUrl(String clientID, String clientSecret, String originalUrl);

    ShortUrlResponseDTO generateShortUrl(String clientID, String clientSecret, String originalUrl);

    ShortUrlResponseDTO updateShortUrl(String clientID, String clientSecret, String originalUrl);

    void deleteShortUrl(String shortUrl);

}
