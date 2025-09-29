package com.rcf.schedulesservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ServiceBookingRequest(
        @NotNull(message = "orderItemId no puede ser nulo")
        Long orderItemId,
        @NotNull(message = "userId no puede ser nulo")
        Long userId,
        Long serviceId,
        @NotNull(message = "technicianUserId no puede ser nulo")
        Long technicianUserId,
        @NotNull(message = "bookingStatusId no puede ser nulo")
        Long bookingStatusId,
        @NotNull(message = "scheduledStart no puede ser nulo")
        LocalDateTime scheduledStart,
        @NotNull(message = "scheduledEnd no puede ser nulo")
        LocalDateTime scheduledEnd,
        @NotBlank(message = "addressSnapshot no puede estar vacio")
        String addressSnapshot,
        String notes
) {
}
