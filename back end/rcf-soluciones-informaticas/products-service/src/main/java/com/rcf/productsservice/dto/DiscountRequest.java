package com.rcf.productsservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record DiscountRequest(
        @NotBlank(message = "code no puede estar vacio")
        String code,
        @NotBlank(message = "description no puede estar vacio")
        String description,
        @NotNull (message = "DiscountType no puede ser nulo")
        Long discountTypeId,
        @NotNull (message = "value no puede ser nulo")
        BigDecimal value,
        @NotNull (message = "startDate no puede ser nulo")
        LocalDateTime startDate,
        @NotNull (message = "endDate no puede ser nulo")
        LocalDateTime endDate
) {
}
