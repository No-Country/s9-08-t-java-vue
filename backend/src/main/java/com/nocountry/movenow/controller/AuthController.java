package com.nocountry.movenow.controller;

import javax.validation.Valid;

import com.nocountry.movenow.auth.service.AuthenticationService;
import com.nocountry.movenow.dto.RegisterDTO;
import com.nocountry.movenow.dto.RequestDTO;
import com.nocountry.movenow.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            return ResponseEntity.ok(authenticationService.register(registerDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authenticate(@Valid @RequestBody RequestDTO requestDTO) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(requestDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }



}
