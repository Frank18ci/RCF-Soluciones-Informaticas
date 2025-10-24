package com.cibertec.securityservice.service;

import com.cibertec.securityservice.client.UserClient;
import com.cibertec.securityservice.client.dto.UserRequest;
import com.cibertec.securityservice.client.dto.UserResponse;
import com.cibertec.securityservice.dto.AuthRequest;
import com.cibertec.securityservice.dto.AuthResponse;
import com.cibertec.securityservice.exception.BadRequest;
import com.cibertec.securityservice.security.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserClient userClient;
    private final JwtUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AuthResponse login(AuthRequest authRequest) {
        UserResponse user = userClient.getUserByEmail(authRequest.username());

        if (user == null) {
            throw new BadRequest("User not found");
        }

        if (!passwordEncoder.matches(authRequest.password(), user.password())) {
            throw new BadRequest("Invalid email or password");
        }
        String token = jwtUtil.generateAccessToken(user.email());
        return AuthResponse.builder()
                .token(token)
                .fullName(user.fullName())
                .username(user.email())
                .roles((List.of(user.role().name())))
                .build();
    }

    @Override
    public UserResponse registerClient(UserRequest userRequest) {
        userRequest = new UserRequest(
                userRequest.email(),
                passwordEncoder.encode(userRequest.password()),
                userRequest.fullName(),
                1L, // Role ID for client
                userRequest.phone(),
                userRequest.active()
        );

        return userClient.createUser(userRequest);
    }

    @Override
    public UserResponse registerAdmin(UserRequest userRequest) {
        userRequest = new UserRequest(
                userRequest.email(),
                passwordEncoder.encode(userRequest.password()),
                userRequest.fullName(),
                2L, // Role ID for admin
                userRequest.phone(),
                userRequest.active()
        );

        return userClient.createUser(userRequest);
    }
}
