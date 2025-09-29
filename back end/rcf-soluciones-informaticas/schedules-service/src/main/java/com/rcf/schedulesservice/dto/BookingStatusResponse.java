package com.rcf.schedulesservice.dto;

import lombok.Builder;

@Builder
public record BookingStatusResponse(
        Long id,
        String code,
        Integer sortOrder
) {
}
