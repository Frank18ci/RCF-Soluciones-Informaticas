package com.cibertec.securityservice.client.dto;

import lombok.Builder;

@Builder
public record RolResponse(
        Long id,
        String code,
        String name
) {
}
