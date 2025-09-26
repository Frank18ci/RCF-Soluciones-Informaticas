package com.rcf.servicesservice.dto;

import lombok.Builder;

@Builder
public record ProductServiceResponse(
        Long id,
        Long serviceId,
        Long productId,
        Long priceOverrideCents
) {
}
