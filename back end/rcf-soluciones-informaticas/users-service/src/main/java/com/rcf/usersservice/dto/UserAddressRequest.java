package com.rcf.usersservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserAddressRequest(
        @NotNull(message = "userId no puede ser nulo")
        Long userId,
        @NotBlank(message = "address no puede estar vacio")
        String address,
        @NotBlank(message = "referenceAddress no puede estar vacio")
        String referenceAddress,
        @NotNull(message = "cityId no puede ser nulo")
        Long cityId,
        @NotBlank(message = "postalCode no puede estar vacio")
        String postalCode
) {
}
