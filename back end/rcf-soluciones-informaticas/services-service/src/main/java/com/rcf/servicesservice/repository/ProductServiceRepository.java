package com.rcf.servicesservice.repository;

import com.rcf.servicesservice.model.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductServiceRepository extends JpaRepository<ProductService, Long> {
}
