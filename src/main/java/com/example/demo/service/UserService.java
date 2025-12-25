package com.example.demo.service;

import com.example.demo.dto.*;

public interface UserService {
    com.example.demo.model.User register(UserRegisterDto dto);
    AuthResponse login(AuthRequest request);
}
