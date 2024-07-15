package com.example.demo.business.dtos.responses.user;

import com.example.demo.core.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String identityNumber;
    private int authorId;
    private Role role;
    private LocalDateTime createdDate;

}
