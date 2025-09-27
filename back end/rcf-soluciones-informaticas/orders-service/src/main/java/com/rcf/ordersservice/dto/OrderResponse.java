package com.rcf.ordersservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderResponse(
        Long id,
        Long userId,
        String currencyCode,
        OrderStatusResponse orderStatus,
        String paymentMethodCode,
        Long subtotalCents,
        Long taxTotalCents,
        Long totalCents,
        Long discountCents,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
