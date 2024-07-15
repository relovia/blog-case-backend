package com.example.demo.business.concretes;

import com.example.demo.business.abstracts.AuthService;
import com.example.demo.business.dtos.requests.login.LoginRequest;
import com.example.demo.business.dtos.requests.register.RegisterRequest;
import com.example.demo.business.dtos.responses.login.LoginResponse;
import com.example.demo.business.dtos.responses.register.RegisterResponse;
import com.example.demo.business.rules.UserBusinessRules;
import com.example.demo.core.enums.Role;
import com.example.demo.core.utilities.exceptions.types.BusinessException;
import com.example.demo.core.utilities.mapping.ModelMapperService;
import com.example.demo.dataAccess.abstracts.UserRepository;
import com.example.demo.entities.concretes.User;
import com.example.demo.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {
    private UserRepository userRepository;
    private ModelMapperService mapperService;
    private UserBusinessRules userBusinessRules;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        userBusinessRules.emailCanNotBeDuplicated(registerRequest.getEmail());

        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .identityNumber(registerRequest.getIdentityNumber())
                .role(Role.USER) // Default role is user
                .build();

        user.setCreatedDate(LocalDateTime.now());
        userRepository.save(user);

        RegisterResponse registerResponse = mapperService.forResponse().map(user, RegisterResponse.class);
        return registerResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        userBusinessRules.checkUserExists(loginRequest.getEmail());
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (!passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword())) {
            throw new BusinessException("Invalid email or password");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            String jwt = null;

            if (authentication.isAuthenticated()) {
                jwt = jwtService.generateToken(loginRequest.getEmail());
            } else {
                throw new BusinessException("Invalid email or password");
            }

            LoginResponse loginResponse = LoginResponse.builder()
                    .id(userOptional.get().getId())
                    .firstName(userOptional.get().getFirstName())
                    .lastName(userOptional.get().getLastName())
                    .email(userOptional.get().getEmail())
                    .role(userOptional.get().getRole().name())
                    .token(jwt)
                    .build();
            return loginResponse;
        } catch (Exception e) {
            throw new BusinessException("Authentication failed." + e.getMessage());
        }
    }
}