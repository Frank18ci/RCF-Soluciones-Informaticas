package com.rcf.productsservice.repository;

import com.rcf.productsservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingAndCategory_IdAndSalePriceCentsBetween(String name, Long categoryId, Long salePriceCentsAfter, Long salePriceCentsBefore);
    List<Product> findByNameContainingAndSalePriceCentsBetween(String name, Long salePriceCentsAfter, Long salePriceCentsBefore);
}
