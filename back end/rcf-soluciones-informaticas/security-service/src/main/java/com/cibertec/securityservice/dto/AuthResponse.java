package com.cibertec.securityservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AuthResponse(
        String token,
        String username,
        String fullName,
        List<String> roles,
        Boolean active
) {
}
