package com.abdallah.product_api.controller;

import com.abdallah.product_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/get")
    public String printString(){
        return "First Controller From Spring Boot";
    }
}
