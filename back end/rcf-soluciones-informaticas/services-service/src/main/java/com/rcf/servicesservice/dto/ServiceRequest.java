package com.rcf.servicesservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ServiceRequest(
        @NotBlank(message = "code no puede estar vacio")
        String code,
        @NotBlank(message = "name no puede estar vacio")
        String name,
        @NotBlank(message = "description no puede estar vacio")
        String description,
        @NotNull(message = "basePriceCents no puede ser nulo")
        Long basePriceCents,
        @NotNull(message = "taxRate no puede ser nulo")
        BigDecimal taxRate,
        @NotNull(message = "durationMinutes no puede ser nulo")
        Integer durationMinutes,
        @NotNull(message = "requiresOnSite no puede ser nulo")
        Boolean requiresOnSite
) {
}
