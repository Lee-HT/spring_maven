package com.example.project2.service.Impl;

import com.example.project2.dto.MemberDTO;
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

    public String getIA(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8050")
                .path("/server/IA")
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
                .path("/server/name")
                .queryParam("name","IA")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}",responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    public String getName2(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8050")
                .path("/server/pathvariable/{name}")
                .encode()
                .build()
                .expand("IA")
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}",responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

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
        memberDTO.setEmail("email.com");
        memberDTO.setOrganization("IA Spring boot");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO,MemberDTO.class);

        LOGGER.info("status code: {}",responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;

    }

    public ResponseEntity<MemberDTO> addHeader(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8050")
                .path("/server/addHeader")
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
