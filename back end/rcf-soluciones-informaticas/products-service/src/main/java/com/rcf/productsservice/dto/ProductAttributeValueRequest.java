package com.rcf.productsservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductAttributeValueRequest(
        @NotBlank(message = "value no puede estar vacio")
        String value,
        @NotNull(message = "productId no puede ser nulo")
        Long productId,
        @NotNull(message = "productAttributeId no puede ser nulo")
        Long productAttributeId
) {
}
