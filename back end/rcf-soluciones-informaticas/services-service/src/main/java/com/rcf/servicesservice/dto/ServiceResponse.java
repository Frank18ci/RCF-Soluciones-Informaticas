package com.rcf.servicesservice.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ServiceResponse(
        Long id,
        String code,
        String name,
        String description,
        Long basePriceCents,
        BigDecimal taxRate,
        Integer durationMinutes,
        Boolean requiresOnSite,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
