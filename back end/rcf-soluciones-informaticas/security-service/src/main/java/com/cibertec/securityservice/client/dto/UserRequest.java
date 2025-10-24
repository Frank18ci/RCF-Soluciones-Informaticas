package com.cibertec.securityservice.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserRequest(
        @Email(message = "email debe tener un formato valido")
        @NotBlank(message = "email no puede estar vacio")
        String email,
        String password,
        @NotBlank(message = "fullName no puede estar vacio")
        String fullName,
        @NotNull(message = "roleId no puede ser nulo")
        Long roleId,
        @NotBlank(message = "phone no puede estar vacio")
        String phone,
        @NotNull(message = "active no puede ser nulo")
        Boolean active
) {
}
