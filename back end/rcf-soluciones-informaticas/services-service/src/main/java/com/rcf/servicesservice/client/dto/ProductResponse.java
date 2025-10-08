package com.rcf.servicesservice.client.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ProductResponse(
        Long id,
        String sku,
        String name,
        String shortDescription,
        String description,
        Long basePriceCents,
        Long purchasePriceCents,
        Long salePriceCents,
        BigDecimal taxRate,
        Integer stock,
        CategoryResponse category,
        DiscountResponse discount,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
}
