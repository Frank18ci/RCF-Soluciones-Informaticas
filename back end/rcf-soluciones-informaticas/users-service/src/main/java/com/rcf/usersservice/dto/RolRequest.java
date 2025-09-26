package com.rcf.usersservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RolRequest(
        @NotBlank(message = "code no puede estar vacio")
        String code,
        @NotBlank(message = "name no puede estar vacio")
        String name
) {
}
