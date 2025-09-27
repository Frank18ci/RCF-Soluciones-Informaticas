package com.rcf.ordersservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemRequest(
        @NotNull(message = "orderId no puede ser nulo")
        Long orderId,
        @NotNull(message = "serviceId no puede ser nulo")
        Long serviceId,
        @NotNull(message = "productId no puede ser nulo")
        Long productId,
        @NotNull(message = "qty no puede ser nulo")
        @Positive(message = "qty debe ser un numero positivo")
        Integer qty,
        @NotNull(message = "unitPriceCents no puede ser nulo")
        @Positive(message = "unitPriceCents debe ser un numero positivo")
        Long unitPriceCents,
        @NotNull(message = "taxRate no puede ser nulo")
        @Positive(message = "taxRate debe ser un numero positivo")
        BigDecimal taxRate,
        @NotNull(message = "totalLineCents no puede ser nulo")
        @Positive(message = "totalLineCents debe ser un numero positivo")
        Long totalLineCents,
        @NotNull(message = "discountLineCents no puede ser nulo")
        @Positive(message = "discountLineCents debe ser un numero positivo")
        Long discountLineCents
) {
}
