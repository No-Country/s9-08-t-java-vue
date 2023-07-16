package com.nocountry.movenow.auth.service;


import com.nocountry.movenow.dto.RegisterDTO;
import com.nocountry.movenow.dto.RequestDTO;
import com.nocountry.movenow.dto.ResponseDTO;
import com.nocountry.movenow.model.enums.Role;
import com.nocountry.movenow.model.UserEntity;
import com.nocountry.movenow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public ResponseDTO register(RegisterDTO request) {

        Optional<UserEntity> optUser = userRepository.findByEmail(request.getEmail());

        if (optUser.isPresent())
            throw new RuntimeException("This username has been already taken.");

        var user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return ResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public ResponseDTO authenticate(RequestDTO request) {
        System.out.println("Email recibido: " + request.getEmail());
        System.out.println("Contraseña recibida: " + request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        //It will continue to below lines only if authentication was successful
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("Usuario encontrado: " + user.getUsername());
        var jwtToken = jwtService.generateToken(user);
        return ResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

}
