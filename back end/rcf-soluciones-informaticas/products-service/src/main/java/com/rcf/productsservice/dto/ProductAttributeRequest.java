package com.rcf.productsservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ProductAttributeRequest(
        @NotBlank(message = "code no puede estar vacio")
        String code,
        @NotBlank(message = "name no puede estar vacio")
        String name
) {
}
