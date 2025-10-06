package com.rcf.ordersservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record OrderRequest(
        @NotNull(message = "userId no puede ser nulo")
        Long userId,
        @NotBlank(message = "currencyCode no puede estar vacio")
        @Length(min = 3, max = 3, message = "currencyCode debe tener 3 caracteres")
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
        @PositiveOrZero(message = "discountCents debe ser mayor o igual a cero")
        Long discountCents,
        @NotBlank(message = "billingAddress no puede estar vacio")
        String notes
) {
}
