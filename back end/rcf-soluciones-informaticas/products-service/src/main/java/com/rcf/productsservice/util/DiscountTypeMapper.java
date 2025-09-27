package com.rcf.productsservice.util;

import com.rcf.productsservice.dto.DiscountTypeRequest;
import com.rcf.productsservice.dto.DiscountTypeResponse;
import com.rcf.productsservice.model.DiscountType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscountTypeMapper {
    DiscountType toEntity(DiscountTypeRequest discountTypeRequest);
    DiscountTypeResponse toDto(DiscountType discountType);
    List<DiscountTypeResponse> tpDtoList(List<DiscountType> discountTypes);
}
