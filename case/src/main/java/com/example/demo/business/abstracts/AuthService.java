package com.example.demo.business.abstracts;

import com.example.demo.business.dtos.requests.login.LoginRequest;
import com.example.demo.business.dtos.requests.register.RegisterRequest;
import com.example.demo.business.dtos.responses.login.LoginResponse;
import com.example.demo.business.dtos.responses.register.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);
}
