package com.example.demo.config;

import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class JwtProvider {
    public String generateToken(String email, Long id, Set<String> roles){return "token";}
    public boolean validateToken(String t){return true;}
    public String getEmailFromToken(String t){return null;}
    public Long getUserId(String t){return null;}
}
