package com.rcf.productsservice.dto;


import lombok.Builder;

@Builder
public record ProductAttributeValueResponse(
        Long id,
        String value,
        ProductResponse product,
        ProductAttributeResponse productAttribute
) {
}
