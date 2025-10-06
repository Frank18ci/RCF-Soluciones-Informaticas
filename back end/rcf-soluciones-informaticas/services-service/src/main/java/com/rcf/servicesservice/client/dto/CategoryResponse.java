package com.rcf.servicesservice.client.dto;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CategoryResponse(
        Long id,
        CategoryResponse parent,
        String name,
        String slug,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
