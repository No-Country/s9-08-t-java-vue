package com.nocountry.movenow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/test")
public class TestController {


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        String message = "Hello, world!";
        return ResponseEntity.ok(message);
    }



}
