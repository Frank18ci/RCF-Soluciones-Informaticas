package com.rcf.ordersservice.dto;

import lombok.Builder;

@Builder
public record OrderStatusResponse(
        Long id,
        String code,
        Integer sortOrder
) {
}
