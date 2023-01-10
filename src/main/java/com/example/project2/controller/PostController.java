package com.example.project2.controller;

import com.example.project2.data.dto.MemberDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {

    @PostMapping("/hello")
    public String postMethod(){
        return "Hello world";
    }

    //localhost:port/request1
    @PostMapping("/request1")
    public String postMember(@RequestBody Map<String, Object> postData){
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map ->{
            sb.append(map.getKey()+":"+map.getValue()+"\n");
        });

        return sb.toString();
    }

    @PostMapping("/request2")
    public String postMember(@RequestBody MemberDTO memberDTO){
        return memberDTO.toString();
    }
}
