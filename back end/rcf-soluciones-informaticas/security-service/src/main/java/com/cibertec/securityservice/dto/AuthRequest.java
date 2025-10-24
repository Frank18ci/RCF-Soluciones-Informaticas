package com.cibertec.securityservice.dto;

import lombok.Builder;

@Builder
public record AuthRequest(
        String username,
        String password
) {
}
