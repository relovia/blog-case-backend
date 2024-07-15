package com.example.demo.business.abstracts;

import com.example.demo.core.enums.Role;
import com.example.demo.entities.concretes.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    public int getCurrentUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    public boolean isAdmin() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getRole() == Role.ADMIN;
    }
}
