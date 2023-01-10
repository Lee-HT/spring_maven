package com.example.project2.data.dao.impl;

import com.example.project2.data.dao.ShortUrlDAO;
import com.example.project2.data.entity.ShortUrlEntity;
import com.example.project2.data.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlDAOImpl implements ShortUrlDAO {
    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    public ShortUrlDAOImpl(ShortUrlRepository shortUrlRepository){
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrl){
        ShortUrlEntity foundShortUrl = shortUrlRepository.save(shortUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrlEntity getShortUrl(String originalUrl){
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrlEntity getOriginUrl(String shortUrl){
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByUrl(shortUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrl){
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(newShortUrl.getUrl());

        foundShortUrl.setUrl(newShortUrl.getUrl());

        ShortUrlEntity saveShortUrl = shortUrlRepository.save(foundShortUrl);

        return saveShortUrl;
    }

    @Override
    public void deleteByShortUrl(String shortUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByUrl(shortUrl);
        shortUrlRepository.delete(foundShortUrl);
    }

    @Override
    public void deleteByOriginUrl(String originalUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        shortUrlRepository.delete(foundShortUrl);
    }
}
