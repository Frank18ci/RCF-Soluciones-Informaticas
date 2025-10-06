package com.rcf.schedulesservice.client.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        Long id,
        String email,
        String password,
        String fullName,
        RolResponse role,
        String phone,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
}
