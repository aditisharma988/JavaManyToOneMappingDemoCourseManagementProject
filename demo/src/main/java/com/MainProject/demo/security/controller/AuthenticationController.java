package com.MainProject.demo.security.controller;


import com.MainProject.demo.security.auth.AuthenticationRequest;
import com.MainProject.demo.security.auth.AuthenticationResponseDto;
import com.MainProject.demo.security.auth.RegisterResponseDto;
import com.MainProject.demo.security.auth.RegisterRequest;
import com.MainProject.demo.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(
            @RequestBody RegisterRequest request)
    {
      return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> register(
            @RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}