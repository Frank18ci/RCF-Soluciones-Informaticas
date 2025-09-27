package com.rcf.productsservice.model;

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
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String sku;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(name ="short_description " , length = 255)
    private String shortDescription;

    @Lob
    private String description;

    @Column(name = "base_price_cents", nullable = false)
    private Long basePriceCents;

    @Column(name = "purchase_price_cents", nullable = false)
    private Long purchasePriceCents;

    @Column(name = "sale_price_cents", nullable = false)
    private Long salePriceCents;

    @Column(name = "tax_rate", precision = 5, scale = 2, nullable = false)
    private Double taxRate;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_products_category"))
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "discount_id", foreignKey = @ForeignKey(name = "fk_products_discount"))
    private Discounts discounts;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
