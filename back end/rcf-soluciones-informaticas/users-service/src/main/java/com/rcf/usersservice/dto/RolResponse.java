package com.rcf.usersservice.dto;

import lombok.Builder;

@Builder
public record RolResponse(
        Long id,
        String code,
        String name
) {
}
