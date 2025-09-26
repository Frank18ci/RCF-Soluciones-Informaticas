package com.rcf.usersservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record StateRegionRequest(
        @NotBlank(message = "name no puede estar vacio")
        String name,
        @NotNull(message = "countryId no puede ser nulo")
        Long countryId
) {
}
