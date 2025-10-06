package com.rcf.ordersservice.client.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record DiscountResponse(
        Long id,
        String name,
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
