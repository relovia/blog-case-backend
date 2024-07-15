package com.example.demo.business.rules;

import com.example.demo.core.utilities.exceptions.types.BusinessException;
import com.example.demo.dataAccess.abstracts.UserRepository;
import com.example.demo.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserBusinessRules {
    private UserRepository userRepository;

    public void emailCanNotBeDuplicated(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new BusinessException("User with this email already exists. Please use another email.");
        }
    }

    public void checkUserExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new BusinessException("There is no user with this email. Please register first.");
        }
    }
}
