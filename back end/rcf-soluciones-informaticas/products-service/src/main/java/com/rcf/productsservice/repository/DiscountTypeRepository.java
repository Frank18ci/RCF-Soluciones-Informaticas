package com.rcf.productsservice.repository;

import com.rcf.productsservice.model.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountTypeRepository extends JpaRepository<DiscountType, Long> {

}
