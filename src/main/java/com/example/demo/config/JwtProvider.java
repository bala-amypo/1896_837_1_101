package com.example.demo.config;

import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class JwtProvider {

    public String generateToken(String email, Long userId, Set<String> roles) {
        return "fake.jwt.token";
    }

    public boolean validateToken(String token) {
        return true;
    }

    public String getEmailFromToken(String token) {
        return null;
    }

    public Long getUserId(String token) {
        return null;
    }
}
