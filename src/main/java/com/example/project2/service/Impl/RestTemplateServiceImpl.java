package com.example.project2.service.Impl;

import com.example.project2.data.dto.MemberDTO;
import com.example.project2.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Override
    public String getIA(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8050")
                .path("/server/get")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();

    }

    @Override
    public String getName(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8050")
                .path("/server/get/name")
                .queryParam("name","IA") //복수 값은 여러 개 사용
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}",responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8050")
                .path("/server/get/pathvariable/{name}")
                .encode()
                .build()
                .expand("IA") //복수 값 넣을시 ,로 구분
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}",responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDTO(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8050")
                .path("/server/member")
                .queryParam("name","IA")
                .queryParam("email","email.com")
                .queryParam("organization", "IA Spring boot")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("IA!!");
        memberDTO.setEmail("emails.com");
        memberDTO.setOrganization("IA Spring boots");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO,MemberDTO.class);

        LOGGER.info("status code: {}",responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;

    }

    @Override
    public ResponseEntity<MemberDTO> addHeader(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8050")
                .path("/server/addheader")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("IA!!");
        memberDTO.setEmail("email.com");
        memberDTO.setOrganization("IA Spring boot");

        RequestEntity<MemberDTO> requestEntity = RequestEntity
                .post(uri)
                .header("IA-header", "IA Spring boot")
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity,MemberDTO.class);

        LOGGER.info("Status code : {}",responseEntity.getStatusCode());
        LOGGER.info("body : {}",responseEntity.getBody());

        return responseEntity;

    }
}