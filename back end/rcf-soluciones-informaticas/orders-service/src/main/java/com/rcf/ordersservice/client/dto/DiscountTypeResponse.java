package com.rcf.ordersservice.client.dto;

import lombok.Builder;

@Builder
public record DiscountTypeResponse(
        Long id,
        String name
) {
}
