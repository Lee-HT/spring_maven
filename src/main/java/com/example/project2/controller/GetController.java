package com.example.project2.controller;

import com.example.project2.data.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/get")
public class GetController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return name + "님 안녕하세요! : 1";
    }

    @GetMapping("/hello2/{name}/{name2}")
    public String hello2(@PathVariable("name") String na, @PathVariable String name2){
        return na + "님 " + name2 + "님 안녕하세요 : 2 ";
    }


    //localhost:port?name=name&email=email@gmail.com&organization=organization
    @GetMapping("/request1")
    public String getRequestParam1(@RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map ->{
            sb.append(map.getKey() + ":" + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @GetMapping("/request3")
    public String getRequestParam3(MemberDTO memberDTO){
        return memberDTO.toString();

    }


}
