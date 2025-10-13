package com.rcf.productsservice.repository;

import com.rcf.productsservice.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findAllByCodeContaining(String code);

}
