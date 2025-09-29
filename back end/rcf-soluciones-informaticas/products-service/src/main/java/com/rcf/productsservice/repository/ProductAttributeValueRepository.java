package com.rcf.productsservice.repository;

import com.rcf.productsservice.model.ProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, Long> {
}
