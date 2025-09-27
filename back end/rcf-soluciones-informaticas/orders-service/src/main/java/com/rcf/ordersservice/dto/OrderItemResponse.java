package com.rcf.ordersservice.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemResponse(
        Long id,
        OrderResponse order,
        Long serviceId,
        Long productId,
        Integer qty,
        Long unitPriceCents,
        BigDecimal taxRate,
        Long totalLineCents,
        Long discountLineCents
) {
}
