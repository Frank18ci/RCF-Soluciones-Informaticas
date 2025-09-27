package com.rcf.ordersservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderStatusRequest(
        @NotBlank(message = "name no puede estar vacio")
        String code,
        @NotNull(message = "description no puede ser nulo")
        Integer sortOrder
) {
}
