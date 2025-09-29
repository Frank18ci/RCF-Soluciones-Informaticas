package com.rcf.schedulesservice.controller;

import com.rcf.schedulesservice.dto.BookingStatusRequest;
import com.rcf.schedulesservice.service.BookingStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking-status")
public class BookingStatusController {
    private final BookingStatusService bookingStatusService;
    @GetMapping
    public ResponseEntity<?> getBookingStatus(){
        return ResponseEntity.ok(bookingStatusService.getAllBookingStatuses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingStatusService.getBookingStatusById(id));
    }
    @PostMapping
    public ResponseEntity<?> createBookingStatus(@RequestBody @Valid BookingStatusRequest bookingStatusRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingStatusService.saveBookingStatus(bookingStatusRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookingStatus(@PathVariable Long id, @RequestBody @Valid BookingStatusRequest bookingStatusRequest) {
        return ResponseEntity.ok(bookingStatusService.updateBookingStatus(id, bookingStatusRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookingStatus(@PathVariable Long id) {
        bookingStatusService.deleteBookingStatus(id);
        return ResponseEntity.noContent().build();
    }
}
