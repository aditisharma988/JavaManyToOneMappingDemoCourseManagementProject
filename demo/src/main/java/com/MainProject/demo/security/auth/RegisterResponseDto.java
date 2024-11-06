package com.MainProject.demo.security.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {

    private String token;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;

}
