package com.rcf.schedulesservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ServiceBookingResponse(
        Long id,
        Long orderItemId,
        Long userId,
        Long serviceId,
        Long technicianUserId,
        BookingStatusResponse bookingStatus,
        LocalDateTime scheduledStart,
        LocalDateTime scheduledEnd,
        String addressSnapshot,
        String notes
) {
}
