package com.rcf.productsservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        String name,
        @NotBlank(message = "El slug no puede estar vacío")
        String slug,
        @NotNull(message = "El parentId no puede estar vacío")
        Long parentId
) {

}
