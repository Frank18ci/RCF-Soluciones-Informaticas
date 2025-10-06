package com.rcf.servicesservice.dto;

import lombok.Builder;

@Builder
public record ProductServiceResponse(
        Long id,
        ServiceResponse service,
        Long productId,
        Long priceOverrideCents
) {
}
