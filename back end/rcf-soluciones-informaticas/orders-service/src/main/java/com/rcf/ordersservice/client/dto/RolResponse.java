package com.rcf.ordersservice.client.dto;

import lombok.Builder;

@Builder
public record RolResponse(
        Long id,
        String code,
        String name
) {
}
