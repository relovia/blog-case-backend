package com.example.demo.business.dtos.responses.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String identityNumber;
    private String role;
    private String token;
    private String message;
    private LocalDateTime createdDate;
}
