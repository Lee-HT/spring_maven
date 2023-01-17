package com.example.project2.service.Impl;

import com.example.project2.data.dao.ShortUrlDAO;
import com.example.project2.data.dto.NaverUrlDTO;
import com.example.project2.data.dto.ShortUrlResponseDTO;
import com.example.project2.data.entity.ShortUrlEntity;
import com.example.project2.data.repository.ShortUrlRedisRepository;
import com.example.project2.data.repository.ShortUrlRepository;
import com.example.project2.service.ShortUrlService;
import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
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

    private final ShortUrlRepository shortUrlRepository;
    private final ShortUrlRedisRepository shortUrlRedisRepository;

    private final ShortUrlDAO shortUrlDAO;
//    private final ShortUrlRedisRepository shortUrlRedisRepository;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO,
            ShortUrlRepository shortUrlRepository, ShortUrlRedisRepository shortUrlRedisRepository){
        this.shortUrlDAO = shortUrlDAO;
//        this.shortUrlRedisRepository = shortUrlRedisRepository;
        this.shortUrlRepository = shortUrlRepository;
        this.shortUrlRedisRepository = shortUrlRedisRepository;
    }

    public ShortUrlResponseDTO getShortUrl(String clientID, String clientSecret, String originalUrl){

        //Cache logic
        Optional<ShortUrlResponseDTO> foundResponseDTO = shortUrlRedisRepository.findById(originalUrl); // null 일수도 있는 객체를 감싸는 wrapper
        if (foundResponseDTO.isPresent()){        // 객체 존재 여부 확인
            log.info("[getShortUrl] Cache Data is existed");
            return foundResponseDTO.get();        // optional 내부 객체 반환
        }else{
            log.info("[getShortUrl] Cache Data is not existed.");
        }

        ShortUrlEntity getShortUrlEntity = shortUrlDAO.getShortUrl(originalUrl);

        String orgUrl;
        String shortUrl;

        if (getShortUrlEntity == null){
            log.info("[getShortUrl] No Entity in Database.");
            ResponseEntity<NaverUrlDTO> responseEntity = requestShortUrl(clientID,clientSecret,originalUrl);

            orgUrl = responseEntity.getBody().getResult().getOrgUrl();
            shortUrl = responseEntity.getBody().getResult().getUrl();
        }else {
            orgUrl = getShortUrlEntity.getOrgUrl();
            shortUrl = getShortUrlEntity.getUrl();
        }

        ShortUrlResponseDTO shortUrlResponseDTO = new ShortUrlResponseDTO(orgUrl, shortUrl);

        shortUrlRedisRepository.save(shortUrlResponseDTO);

        log.info("[getShortUrl] Response DTO : {}",shortUrlResponseDTO.toString());
        return shortUrlResponseDTO;
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

        //Cache logic
        shortUrlRedisRepository.save(shortUrlResponseDTO);

        log.info("[generateShortUrl] Response DTO : {}",shortUrlResponseDTO.toString());
        return shortUrlResponseDTO;
    }

    @Override
    public ShortUrlResponseDTO updateShortUrl(String clientID, String clientSecret, String originalUrl){
        return null;
    }

    @Override
    public void deleteShortUrl(String url){
        if(url.contains("me2.do")){
            log.info("[deleteShortUrl] Request Url is ShortUrl");
            deleteByShortUrl(url);
        }else {
            log.info("[deleteShortUrl Request Url is OriginalUrl");
            deleteByOriginalUrl(url);
        }

    }

    private void deleteByShortUrl(String url){
        log.info("[deleteByShortUrl] delete record");
        shortUrlDAO.deleteByShortUrl(url);
    }

    private void deleteByOriginalUrl(String url){
        log.info("[deleteByOriginalUrl] delete record");
        shortUrlDAO.deleteByOriginalUrl(url);
    }


    private ResponseEntity<NaverUrlDTO> requestShortUrl(String ClientId,String ClientSecret,String originalUrl){

        log.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : {}",originalUrl);

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/util/shorturl")
                .queryParam("url",originalUrl)
                .encode()
                .build()
                .toUri();

        log.info("[requestShortUrl] set HTTP Request Header");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id",ClientId);
        headers.set("X-Naver-Client-Secret",ClientSecret);

        HttpEntity<String> entity = new HttpEntity<>("",headers);

        RestTemplate restTemplate = new RestTemplate();

        log.info("[requestShortUrl] request by restTemplate");
        ResponseEntity<NaverUrlDTO> responseEntity = restTemplate.exchange(
                uri, HttpMethod.GET, entity, NaverUrlDTO.class);

        log.info("[requestShortUrl] request has been successfully complete");
        log.info("entity : {}" ,entity);

        return responseEntity;
    }
}
