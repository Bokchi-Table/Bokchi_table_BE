package com.bokchitable.service;

import com.bokchitable.domain.User;
import com.bokchitable.dto.request.UserRequest;
import com.bokchitable.dto.response.UserResponse;
import com.bokchitable.repository.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse signup(UserRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new DuplicateKeyException("email_exists");
        }
        if (req.getPhone() != null && !req.getPhone().isBlank() &&
            userRepository.existsByPhone(req.getPhone())) {
            throw new DuplicateKeyException("phone_exists");
        }

        User u = new User();
        u.setEmail(req.getEmail());
        u.setPhone(req.getPhone());
        u.setPasswordHash(passwordEncoder.encode(req.getPassword()));

        User saved = userRepository.save(u);
        return UserResponse.from(saved);
    }
}