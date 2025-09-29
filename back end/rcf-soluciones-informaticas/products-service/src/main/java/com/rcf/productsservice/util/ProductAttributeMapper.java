package com.rcf.productsservice.util;

import com.rcf.productsservice.dto.ProductAttributeRequest;
import com.rcf.productsservice.dto.ProductAttributeResponse;
import com.rcf.productsservice.model.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper (componentModel = "spring" )
public interface ProductAttributeMapper {
    ProductAttribute toEntity(ProductAttributeRequest productAttributeRequest);
    ProductAttributeResponse toDto(ProductAttribute productAttribute);
    List <ProductAttributeResponse> toDtoList(List<ProductAttribute> productAttributes);
}
