package com.example.demo.business.dtos.responses.user;

import com.example.demo.core.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String identityNumber;
    private int authorId;
    private Role role;
    private String message;
    private LocalDateTime createdDate;
}
