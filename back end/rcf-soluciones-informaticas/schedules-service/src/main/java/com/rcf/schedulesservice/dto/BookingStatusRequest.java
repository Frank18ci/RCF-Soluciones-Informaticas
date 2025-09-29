package com.rcf.schedulesservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookingStatusRequest(
        @NotBlank(message = "code no puede estar vacio")
       String code,
       @NotNull(message = "sortOrder no puede ser nulo")
       Integer sortOrder
) {
}
