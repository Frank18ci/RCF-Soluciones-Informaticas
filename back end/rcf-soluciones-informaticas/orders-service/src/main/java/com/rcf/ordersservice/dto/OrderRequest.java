package com.rcf.ordersservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record OrderRequest(
        @NotNull(message = "userId no puede ser nulo")
        Long userId,
        @NotBlank(message = "currencyCode no puede estar vacio")
        String currencyCode,
        @NotNull(message = "orderStatusId no puede ser nulo")
        Long orderStatusId,
        @NotBlank(message = "paymentMethodCode no puede estar vacio")
        String paymentMethodCode,
        @NotNull(message = "subtotalCents no puede ser nulo")
        @Positive(message = "subtotalCents debe ser mayor a cero")
        Long subtotalCents,
        @NotNull(message = "taxTotalCents no puede ser nulo")
        @Positive(message = "taxTotalCents debe ser mayor a cero")
        Long taxTotalCents,
        @NotNull(message = "totalCents no puede ser nulo")
        @Positive(message = "totalCents debe ser mayor a cero")
        Long totalCents,
        @NotNull(message = "discountCents no puede ser nulo")
        @Positive(message = "discountCents debe ser mayor o igual a cero")
        Long discountCents,
        @NotBlank(message = "billingAddress no puede estar vacio")
        String notes
) {
}
