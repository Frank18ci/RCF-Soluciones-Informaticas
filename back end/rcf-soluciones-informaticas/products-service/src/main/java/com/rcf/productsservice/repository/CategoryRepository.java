package com.rcf.productsservice.repository;

import com.rcf.productsservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameContaining(String name);

    // Buscar categoría por slug (opcional)
    Optional<Category> findBySlug(String slug);

    // Verificar si existe una categoría con un nombre
    boolean existsByName(String name);

    // Verificar si existe una categoría con un slug
    boolean existsBySlug(String slug);
}
