package com.rcf.schedulesservice.controller;

import com.rcf.schedulesservice.dto.ServiceBookingRequest;
import com.rcf.schedulesservice.service.ServiceBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service-bookings")
public class ServiceBookingController {
    private final ServiceBookingService serviceBookingService;
    @GetMapping
    public ResponseEntity<?> getAllServiceBookings() {
        return ResponseEntity.ok(serviceBookingService.getAllServiceBookings());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceBookingService.getServiceBookingById(id));
    }
    @PostMapping
    public ResponseEntity<?> createServiceBooking(@RequestBody @Valid ServiceBookingRequest serviceBookingRequest) {
        return ResponseEntity.ok(serviceBookingService.createServiceBooking(serviceBookingRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateServiceBooking(@PathVariable Long id, @RequestBody @Valid ServiceBookingRequest serviceBookingRequest) {
        return ResponseEntity.ok(serviceBookingService.updateServiceBooking(id, serviceBookingRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServiceBooking(@PathVariable Long id) {
        serviceBookingService.deleteServiceBooking(id);
        return ResponseEntity.noContent().build();
    }
}
