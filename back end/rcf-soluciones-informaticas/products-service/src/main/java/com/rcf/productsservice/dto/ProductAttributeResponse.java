package com.rcf.productsservice.dto;

import lombok.Builder;

@Builder
public record ProductAttributeResponse(
        Long id,
        String code,
        String name
) {
}
