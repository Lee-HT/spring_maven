package com.example.project2.controller;


import com.example.project2.data.dto.MemberDTO;
import com.example.project2.service.RestTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
public class RestTemplateController {

    RestTemplateService restTemplateService;

    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping(value = "/IA")
    public String getIA(){
        return restTemplateService.getIA();
    }

    @GetMapping(value = "/name")
    public String getName(){
        return restTemplateService.getName();
    }

    @GetMapping(value = "/name2")
    public String getName2() {
        return restTemplateService.getName2();
    }

    @PostMapping(value = "/postdto")
    public ResponseEntity<MemberDTO> postDTO(){
        return restTemplateService.postDTO();
    }

    @PostMapping(value = "/addheader")
    public ResponseEntity<MemberDTO> addHeader(){
        return restTemplateService.addHeader();
    }
}
