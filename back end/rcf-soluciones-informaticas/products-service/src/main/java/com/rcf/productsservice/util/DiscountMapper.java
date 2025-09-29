package com.rcf.productsservice.util;

import com.rcf.productsservice.dto.DiscountRequest;
import com.rcf.productsservice.dto.DiscountResponse;
import com.rcf.productsservice.model.Discount;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DiscountTypeMapper.class})
public interface DiscountMapper {
    @Mapping(source = "discountTypeId", target = "discountType.id")
    Discount toEntity(DiscountRequest discountRequest);
    DiscountResponse toDto(Discount discount);
    List<DiscountResponse> toDtoList(List<Discount> discounts);
}
