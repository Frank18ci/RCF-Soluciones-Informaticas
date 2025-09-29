package com.rcf.schedulesservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "service_bookings")
public class ServiceBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long orderItemId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long serviceId;
    private Long technicianUserId;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private BookingStatus bookingStatus;
    @Column(nullable = false)
    private LocalDateTime scheduledStart;
    @Column(nullable = false)
    private LocalDateTime scheduledEnd;
    @Column(length = 250)
    private String addressSnapshot;
    @Column(columnDefinition = "TEXT")
    private String notes;
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime updatedAt;
}
