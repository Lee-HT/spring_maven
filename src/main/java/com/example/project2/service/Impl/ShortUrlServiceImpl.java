package com.example.project2.service.Impl;

import com.example.project2.data.dao.ShortUrlDAO;
import com.example.project2.data.dto.NaverUrlDTO;
import com.example.project2.data.dto.ShortUrlResponseDTO;
import com.example.project2.data.entity.ShortUrlEntity;
import com.example.project2.service.ShortUrlService;
import java.net.URI;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class ShortUrlServiceImpl implements ShortUrlService {

    private final ShortUrlDAO shortUrlDAO;
//    private final ShortUrlRedisRepository shortUrlRedisRepository;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO){
        this.shortUrlDAO = shortUrlDAO;
//        this.shortUrlRedisRepository = shortUrlRedisRepository;
    }

    public ShortUrlResponseDTO getShortUrl(String clientID, String clientSecret, String originalUrl){
        return null;

    }

    @Override
    public ShortUrlResponseDTO generateShortUrl(String clientID, String clientSecret, String originalUrl){

        log.info("[generateShortUrl] request data : {}", originalUrl);
        ResponseEntity<NaverUrlDTO> responseEntity = requestShortUrl(clientID, clientSecret, originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);

        shortUrlDAO.saveShortUrl(shortUrlEntity);

        ShortUrlResponseDTO shortUrlResponseDTO = new ShortUrlResponseDTO(orgUrl, shortUrl);
        log.info("[generateShortUrl] Response DTO : {}",shortUrlResponseDTO.toString());
        return shortUrlResponseDTO;
    }

    @Override
    public ShortUrlResponseDTO updateShortUrl(String clientID, String clientSecret, String originalUrl){
        return null;
    }

    @Override
    public ShortUrlResponseDTO deleteByShortUrl(String shortURl){
        return null;
    }

    @Override
    public ShortUrlResponseDTO deleteByOriginalUrl(String orgUrl){
        return null;
    }

    private ResponseEntity<NaverUrlDTO> requestShortUrl(String ClientId,String ClientSecret,String originalUrl){

        log.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : {}",originalUrl);

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/util/sho rturl")
                .queryParam("url",originalUrl)
                .encode()
                .build()
                .toUri();

        log.info("[requestShortUrl] set HTTP Request Header");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-naver-Client-ID",ClientId);
        headers.set("X-naver-Client-ID",ClientSecret);

        HttpEntity<String> entity = new HttpEntity<>("",headers);

        RestTemplate restTemplate = new RestTemplate();

        log.info("[requestShortUrl] request by restTemplate");
        ResponseEntity<NaverUrlDTO> responseEntity = restTemplate.exchange(
                uri, HttpMethod.GET, entity, NaverUrlDTO.class);

        log.info("[requestShortUrl] request has been successfully complete");

        return responseEntity;
    }
}
