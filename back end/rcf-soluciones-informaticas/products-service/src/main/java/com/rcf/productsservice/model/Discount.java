package com.rcf.productsservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String code;

    @Column(length = 150)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private DiscountType discountType;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal value;

    @Column(nullable = false)
    private Boolean active = true;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime startDate;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime endDate;

    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "discount")
    private List<Product> products;
}
