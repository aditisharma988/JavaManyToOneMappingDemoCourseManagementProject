package com.MainProject.demo.security.service;

import com.MainProject.demo.security.auth.AuthenticationRequest;
import com.MainProject.demo.security.auth.AuthenticationResponseDto;
import com.MainProject.demo.security.auth.RegisterRequest;
import com.MainProject.demo.security.auth.RegisterResponseDto;
import com.MainProject.demo.security.models.Role;
import com.MainProject.demo.security.models.Users;
import com.MainProject.demo.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public RegisterResponseDto register(RegisterRequest request){
        var user= Users.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .contactNo(request.getContactNo())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken=jwtService.generateToken(user);
        return RegisterResponseDto.builder()
                .token(jwtToken)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .contactNo(request.getContactNo())
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken=jwtService.generateToken(user);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();

    }
}
