package com.example.demo.security;

import com.example.demo.model.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final String secret = "replace-with-strong-secret";
    private final long expiryMillis = 1000L * 60 * 60 * 8; // 8 hours

    public String generateToken(Long userId, String email, Set<Role> roles) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles.stream().map(Enum::name).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(expiryMillis)))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }
}
