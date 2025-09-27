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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String sku;

    @Column(length = 150, nullable = false)
    private String name;
    private String shortDescription;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Long basePriceCents;

    @Column(nullable = false)
    private Long purchasePriceCents;

    @Column(nullable = false)
    private Long salePriceCents;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal taxRate;

    @Column(nullable = false)
    private Integer stock = 0;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_products_category"))
    private Category category;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_products_discount"))
    private Discount discount;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "product")
    private List<ProductAttributeValue> productAttributeValues;
}
