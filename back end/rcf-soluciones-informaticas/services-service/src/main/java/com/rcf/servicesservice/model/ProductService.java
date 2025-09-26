package com.rcf.servicesservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "product_services",
        uniqueConstraints = @UniqueConstraint(name = "uq_service_product", columnNames = {"service_id", "product_id"})
)
public class ProductService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long serviceId;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Long priceOverrideCents;
}
