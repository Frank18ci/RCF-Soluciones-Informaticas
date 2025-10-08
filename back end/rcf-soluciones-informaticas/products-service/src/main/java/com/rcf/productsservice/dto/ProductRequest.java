package com.rcf.productsservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ProductRequest(
        @NotBlank(message = "sku no puede estar vacio")
        String sku,
        @NotBlank(message = "name no puede estar vacio")
        String name,
        @NotBlank(message = "shortDescription no puede estar vacio")
        String shortDescription,
        @NotBlank(message = "description no pude estar vacio")
        String description,
        @NotNull(message = "basePriceCents no pude ser nulo")
        @Positive (message = "basePriceCents debe ser un valor positivo")
        Long basePriceCents,
        @NotNull(message = "purchasePriceCents no pude ser nulo")
        @Positive(message = "purchasePriceCents debe ser un valor positivo")
        Long purchasePriceCents,
        @NotNull (message = "salePriceCents no pude ser nulo")
        @Positive (message = "salePriceCents debe ser un valor positivo")
        Long salePriceCents,
        @NotNull(message = "taxRate no pude ser nulo")
        BigDecimal taxRate,
        @NotNull(message = "stock no pude ser nulo")
        @Positive (message = "stock debe ser un valor positivo")
        Integer stock,
        @NotNull(message = "category no pude ser nulo")
        Long categoryId,
        @NotNull(message = "discount no pude ser nulo")
        Long discountId,
        @NotNull(message = "active no pude ser nulo")
        Boolean active
) {
}
