package com.nocountry.movenow.controller;

import javax.validation.Valid;

import com.nocountry.movenow.auth.service.AuthenticationService;
import com.nocountry.movenow.dto.RegisterDTO;
import com.nocountry.movenow.dto.RequestDTO;
import com.nocountry.movenow.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "MOVE NOW API", version = "1.0.0", description = "API for movings"))
@Tag(name = "Authentication Controller", description = "Controller for Register and Login")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Operation(summary = "Register")
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(
            @Parameter(description = "Email, username and password", required = true)
            @Valid @RequestBody RegisterDTO registerDTO) {
        try {
            return ResponseEntity.ok(authenticationService.register(registerDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Login")
    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authenticate(
            @Parameter(description = "Email and password", required = true)
            @Valid @RequestBody RequestDTO requestDTO) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(requestDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }



}
