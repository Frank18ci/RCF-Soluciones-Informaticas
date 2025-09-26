package com.rcf.usersservice.dto;

import lombok.Builder;

@Builder
public record StateRegionResponse(
        Long id,
        String name,
        CountryResponse country
) {
}
