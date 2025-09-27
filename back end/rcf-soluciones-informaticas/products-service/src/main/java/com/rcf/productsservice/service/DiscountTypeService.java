package com.rcf.productsservice.service;

import com.rcf.productsservice.dto.DiscountTypeRequest;
import com.rcf.productsservice.dto.DiscountTypeResponse;

import java.util.List;

public interface DiscountTypeService {
    List<DiscountTypeResponse> getAllDiscountTypes();
    DiscountTypeResponse getDiscountTypeById(Long id);
    DiscountTypeResponse saveDiscountType(DiscountTypeRequest discountTypeRequest);
    DiscountTypeResponse updateDiscountType(Long id, DiscountTypeRequest discountTypeRequest);
    void deleteDiscountType(Long id);
}
