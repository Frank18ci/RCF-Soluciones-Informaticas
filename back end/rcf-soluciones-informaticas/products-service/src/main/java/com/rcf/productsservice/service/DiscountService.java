package com.rcf.productsservice.service;
import com.rcf.productsservice.dto.DiscountRequest;
import com.rcf.productsservice.dto.DiscountResponse;

import java.util.List;
public interface DiscountService {
    List <DiscountResponse> getAllDiscounts();
    DiscountResponse getDiscountById(Long id);
    DiscountResponse createDiscount(DiscountRequest discountRequest);
    DiscountResponse updateDiscount(Long id, DiscountRequest discountRequest);
    void deleteDiscount(Long id);

    List <DiscountResponse> searchDiscountsByCode(String code);
}
