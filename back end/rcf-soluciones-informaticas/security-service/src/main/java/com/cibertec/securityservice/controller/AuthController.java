package com.cibertec.securityservice.controller;

import com.cibertec.securityservice.client.dto.UserRequest;
import com.cibertec.securityservice.dto.AuthRequest;
import com.cibertec.securityservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }
    @PostMapping("/register-client")
    public ResponseEntity<?> registerClient(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(authService.registerClient(userRequest));
    }
    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(authService.registerClient(userRequest));
    }
}
