package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthResponse {
    private String token;
    private Long userId;
    private String email;
    private Set<String> roles;
}
