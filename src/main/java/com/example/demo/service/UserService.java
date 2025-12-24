package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;

public interface UserService {

    AuthResponse login(AuthRequest request);

    AuthResponse register(UserRegisterDto dto);
}
