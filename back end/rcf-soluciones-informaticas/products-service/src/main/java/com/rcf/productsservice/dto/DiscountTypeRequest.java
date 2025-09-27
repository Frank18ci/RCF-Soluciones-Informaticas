package com.rcf.productsservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DiscountTypeRequest(
        @NotBlank(message = "name no puede estar vacio")
        String name
) {
}
