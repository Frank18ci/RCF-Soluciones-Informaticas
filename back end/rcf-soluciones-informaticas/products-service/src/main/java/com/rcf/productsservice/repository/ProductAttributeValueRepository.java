package com.rcf.productsservice.repository;

import com.rcf.productsservice.model.ProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, Long> {
    List<ProductAttributeValue> findByProductId(Long productId);
}
