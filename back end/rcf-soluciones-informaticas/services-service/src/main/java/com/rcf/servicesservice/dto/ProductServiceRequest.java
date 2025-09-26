package com.rcf.servicesservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductServiceRequest(
        @NotNull(message = "serviceId no puede ser nulo")
        Long serviceId,
        @NotNull(message = "productId no puede ser nulo")
        Long productId,
        @NotNull(message = "priceOverrideCents no puede ser nulo")
        Long priceOverrideCents
) {
}
