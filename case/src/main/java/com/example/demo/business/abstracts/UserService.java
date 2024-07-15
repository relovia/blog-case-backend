package com.example.demo.business.abstracts;

import com.example.demo.business.dtos.requests.user.CreateUserRequest;
import com.example.demo.business.dtos.requests.user.UpdateUserRequest;
import com.example.demo.business.dtos.responses.user.CreateUserResponse;
import com.example.demo.business.dtos.responses.user.GetAllUserResponse;
import com.example.demo.business.dtos.responses.user.GetUserByIdResponse;
import com.example.demo.business.dtos.responses.user.UpdateUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    List<GetAllUserResponse> getAllUsers();

    GetUserByIdResponse getUserById(int id);

    UpdateUserResponse updateUser(UpdateUserRequest request);

    void deleteUser(int id);
}
