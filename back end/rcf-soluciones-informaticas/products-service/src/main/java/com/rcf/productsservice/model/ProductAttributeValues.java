package com.rcf.productsservice.model;

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
        name = "product_attribute_values",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_prod_attr", columnNames = {"product_id", "attribute_id"})
        }
)
public class ProductAttributeValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pav_product"))
    private Products product;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pav_attribute"))
    private ProductAttributes attribute;
}
