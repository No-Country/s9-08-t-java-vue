package com.nocountry.movenow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("api/test")
@Tag(name = "Test Controller", description = "Test for admin role")
public class TestController {

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Hello Test Controller", description = "Test for admin role")
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        String message = "Hello, world!";
        return ResponseEntity.ok(message);
    }



}
