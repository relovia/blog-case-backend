package com.example.demo.business.dtos.responses.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String token;
}
