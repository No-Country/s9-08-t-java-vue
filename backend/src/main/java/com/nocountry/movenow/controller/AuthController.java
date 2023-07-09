package com.nocountry.movenow.controller;

import javax.validation.Valid;

import com.nocountry.movenow.auth.service.AuthenticationService;
import com.nocountry.movenow.dto.RegisterDTO;
import com.nocountry.movenow.dto.RequestDTO;
import com.nocountry.movenow.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(authenticationService.register(registerDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authenticate(@Valid @RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok(authenticationService.authenticate(requestDTO));
    }



}
