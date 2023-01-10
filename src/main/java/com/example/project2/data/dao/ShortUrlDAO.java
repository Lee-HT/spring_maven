package com.example.project2.data.dao;

import com.example.project2.data.entity.ShortUrlEntity;

public interface ShortUrlDAO {
    ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrlEntity);

    ShortUrlEntity getShortUrl(String OriginalUrl);

    ShortUrlEntity getOriginUrl(String shortUrl);

    ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrl);

    void deleteByShortUrl(String shortUrl);

    void deleteByOriginUrl(String originalUrl);
}
