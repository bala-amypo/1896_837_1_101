package com.example.demo.service.impl;

import com.example.demo.config.JwtProvider;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User register(UserRegisterDto dto) {
        if (dto.getName() == null || dto.getName().isBlank() ||
            dto.getPassword() == null || dto.getPassword().isBlank() ||
            dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Name, email and password are required");
        }

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = new HashSet<>();
        if (dto.getRoles() == null || dto.getRoles().isEmpty()) {
            roles.add(Role.ROLE_USER);
        } else {
            roles = dto.getRoles().stream()
                    .map(r -> Role.valueOf(r.toUpperCase()))
                    .collect(Collectors.toSet());
        }
        user.setRoles(roles);

        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        Set<String> roleStrings = user.getRoles().stream()
                .map(Role::name)
                .collect(Collectors.toSet());

        String token = jwtProvider.generateToken(user.getEmail(), user.getId(), roleStrings);

        return new AuthResponse(token, user.getId(), user.getEmail(), roleStrings);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}