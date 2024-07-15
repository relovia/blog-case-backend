package com.example.demo.webApi;

import com.example.demo.business.abstracts.AuthService;
import com.example.demo.business.dtos.requests.login.LoginRequest;
import com.example.demo.business.dtos.requests.register.RegisterRequest;
import com.example.demo.business.dtos.responses.login.LoginResponse;
import com.example.demo.business.dtos.responses.register.RegisterResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", ""})
public class AuthenticationController {
    private AuthService authenticationService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody @Valid RegisterRequest registerRequest) {
        return authenticationService.register(registerRequest);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
