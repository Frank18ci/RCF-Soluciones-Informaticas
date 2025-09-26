package com.rcf.usersservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserAddressResponse(
        Long id,
        UserResponse user,
        String address,
        String referenceAddress,
        CityResponse city,
        String postalCode,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
