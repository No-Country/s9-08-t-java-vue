package com.nocountry.movenow.auth.service;


import com.nocountry.movenow.dto.RegisterDTO;
import com.nocountry.movenow.dto.RequestDTO;
import com.nocountry.movenow.dto.ResponseDTO;
import com.nocountry.movenow.exception.UserAlreadyCreatedException;
import com.nocountry.movenow.model.enums.Role;
import com.nocountry.movenow.model.UserEntity;
import com.nocountry.movenow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public ResponseDTO register(RegisterDTO request) {

        Optional<UserEntity> optUser = userRepository.findByEmail(request.getEmail());

        if (optUser.isPresent())
            throw new UserAlreadyCreatedException("This username has been already taken.");

        var user = UserEntity.builder()
                .email(request.getEmail())
                .user_name(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Set.of(Role.ROLE_USER))
                .softDelete(false)
                .build();
        user.setSoftDelete(Boolean.FALSE);

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return ResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public ResponseDTO authenticate(RequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        //It will continue to below lines only if authentication was successful
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User with that email not found"));
        var jwtToken = jwtService.generateToken(user);
        return ResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

}
