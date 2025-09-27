package com.rcf.productsservice.dto;

import lombok.Builder;

@Builder
public record DiscountTypeResponse(
        Long id,
        String name
) {
}
