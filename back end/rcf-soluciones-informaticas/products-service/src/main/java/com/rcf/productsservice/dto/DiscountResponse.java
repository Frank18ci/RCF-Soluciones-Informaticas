package com.rcf.productsservice.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record DiscountResponse(
        Long id,
        String code,
        String description,
        DiscountTypeResponse discountType,
        BigDecimal value,
        Boolean active,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
