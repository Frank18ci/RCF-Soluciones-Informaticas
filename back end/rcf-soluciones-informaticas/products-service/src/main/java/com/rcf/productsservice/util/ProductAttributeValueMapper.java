package com.rcf.productsservice.util;

import com.rcf.productsservice.dto.ProductAttributeValueRequest;
import com.rcf.productsservice.dto.ProductAttributeValueResponse;
import com.rcf.productsservice.model.ProductAttributeValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductAttributeMapper.class, ProductMapper.class})
public interface ProductAttributeValueMapper {
    @Mapping(source = "productAttributeId", target = "productAttribute.id")
    @Mapping(source = "productId", target = "product.id")
    ProductAttributeValue toEntity(ProductAttributeValueRequest productAttributeValueRequest);
    ProductAttributeValueResponse toDto(ProductAttributeValue productAttributeValue);
    List<ProductAttributeValueResponse> toDtoList(List<ProductAttributeValue> productAttributeValues);
}
