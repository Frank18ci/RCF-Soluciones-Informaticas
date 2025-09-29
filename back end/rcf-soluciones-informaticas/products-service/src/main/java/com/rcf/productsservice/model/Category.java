package com.rcf.productsservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Relación recursiva con la misma tabla (self-referencing)
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_categories_parent"))
    private Category parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> children;
    @Column(length = 120, nullable = false, unique = true)
    private String name;
    @Column(length = 140, nullable = false, unique = true)
    private String slug;
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "parent")
    private List<Category> subcategories;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
