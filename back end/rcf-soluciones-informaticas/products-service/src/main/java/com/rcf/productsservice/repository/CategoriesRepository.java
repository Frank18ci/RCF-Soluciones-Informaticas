package com.rcf.productsservice.repository;

import com.rcf.productsservice.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    // Buscar categoría por nombre (opcional)
    Optional<Categories> findByName(String name);

    // Buscar categoría por slug (opcional)
    Optional<Categories> findBySlug(String slug);

    // Verificar si existe una categoría con un nombre
    boolean existsByName(String name);

    // Verificar si existe una categoría con un slug
    boolean existsBySlug(String slug);
}
