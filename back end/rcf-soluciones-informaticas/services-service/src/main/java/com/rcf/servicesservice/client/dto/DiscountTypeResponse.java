package com.rcf.servicesservice.client.dto;

import lombok.Builder;

@Builder
public record DiscountTypeResponse(
        Long id,
        String name
) {
}
