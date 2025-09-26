package com.rcf.usersservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CityRequest(
        @NotBlank(message = "name no puede estar vacio")
        String name,
        @NotNull(message = "stateRegionId no puede ser nulo")
        Long stateRegionId
        ) {
}
