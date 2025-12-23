package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;

public interface UserService {
    User register(UserRegisterDto dto);
    AuthResponse login(AuthRequest request);
    User getByEmail(String email);
}
