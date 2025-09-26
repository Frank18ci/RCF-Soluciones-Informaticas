package com.rcf.usersservice.dto;

import lombok.Builder;

@Builder
public record CountryResponse(
        Long id,
        String name,
        String code
) {
}
