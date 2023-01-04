package com.example.project2.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delete")
public class DeleteController {

    @DeleteMapping("/delete/{variable}")
    public String DeleteVariable(@PathVariable String variable){
        return variable;
    }
}
