package com.rcf.productsservice.repository;

import com.rcf.productsservice.model.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository <ProductAttribute,Long> {

}
