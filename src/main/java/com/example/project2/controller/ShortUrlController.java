package com.example.project2.controller;

import com.example.project2.data.dto.ShortUrlResponseDTO;
import com.example.project2.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/short-url")
@Slf4j
public class ShortUrlController {

    @Value("${IA.short.url.id}")
    private String CLIENT_ID;

    @Value("${IA.short.url.secret}")
    private String CLIENT_SECRET;

    ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlController(ShortUrlService shortUrlService){
        this.shortUrlService = shortUrlService;
    }

    @PostMapping()
    @Operation(summary = "ShortUrl 생성",description = "기본 주소")
    public ShortUrlResponseDTO generatedShortUrl(String originalUrl){
        log.info("[generateShortUrl] perform API. CLIENT_ID : {} CLIENT_SECRET : {}",CLIENT_ID,CLIENT_SECRET);

        return shortUrlService.generateShortUrl(CLIENT_ID,CLIENT_SECRET,originalUrl);
    }

    @GetMapping()
    @Operation(summary = "ShortUrl 검색",description = "기본 주소")
    public ShortUrlResponseDTO getShortUrl(String originalUrl){
        long startTime = System.currentTimeMillis();
        ShortUrlResponseDTO shortUrlResponseDTO = shortUrlService.getShortUrl(CLIENT_ID,CLIENT_SECRET,originalUrl);
        long endTime = System.currentTimeMillis();

        log.info("[getShortUrl] responsne time : {}ms",(endTime - startTime));

        return shortUrlResponseDTO;
    }

    @PutMapping("/")
    @Operation(summary = "사용 X")
    public ShortUrlResponseDTO updateShortUrl(String originalUrl) { return null; }

    @DeleteMapping("/")
    @Operation(summary = "ShortUrl 삭제",description = "축소한 주소")
    public ResponseEntity<String> deleteShortUrl(String url){
        try{
            shortUrlService.deleteShortUrl(url);
        }catch (RuntimeException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body("정상 삭제");
    }

}
