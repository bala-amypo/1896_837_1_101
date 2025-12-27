package com.example.demo.controller;

import com.example.demo.config.JwtProvider;
import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public User register(@RequestBody UserRegisterDto dto) {
        if(userRepo.findByEmail(dto.getEmail()).isPresent()) throw new RuntimeException("Exists");
        User u = User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .password(encoder.encode(dto.getPassword()))
            .roles(Set.of(Role.ROLE_USER))
            .build();
        return userRepo.save(u);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        User u = userRepo.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("Invalid"));
        if(!encoder.matches(req.getPassword(), u.getPassword())) throw new RuntimeException("Invalid");
        
        Set<String> roles = u.getRoles().stream().map(Enum::name).collect(Collectors.toSet());
        String token = jwtProvider.generateToken(u.getEmail(), u.getId(), roles);
        return new AuthResponse(token, u.getId(), u.getEmail(), roles);
    }
}