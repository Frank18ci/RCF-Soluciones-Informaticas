package com.cibertec.securityservice.service;

import com.cibertec.securityservice.client.dto.UserRequest;
import com.cibertec.securityservice.client.dto.UserResponse;
import com.cibertec.securityservice.dto.AuthRequest;
import com.cibertec.securityservice.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
    UserResponse registerClient(UserRequest userRequest);
    UserResponse registerAdmin(UserRequest userRequest);
}
