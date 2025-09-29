package com.rcf.ordersservice.model;

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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false, columnDefinition = "CHAR(3)")
    private String currencyCode;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus orderStatus;
    @Column(nullable = false, length = 40)
    private String paymentMethodCode;
    @Column(nullable = false)
    private Long subtotalCents = 0L;
    @Column(nullable = false)
    private Long taxTotalCents = 0L;
    @Column(nullable = false)
    private Long totalCents = 0L;
    @Column(nullable = false)
    private Long discountCents = 0L;
    @Column(columnDefinition = "TEXT")
    private String notes;
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime updatedAt;
}