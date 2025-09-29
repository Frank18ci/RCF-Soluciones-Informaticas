package com.rcf.productsservice.util;

import com.rcf.productsservice.dto.ProductRequest;
import com.rcf.productsservice.dto.ProductResponse;
import com.rcf.productsservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper (componentModel = "spring", uses = {CategoryMapper.class, DiscountMapper.class})
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "discountId", target = "discount.id")
    Product toEntity(ProductRequest productRequest);
    ProductResponse toDto(Product product);
    List<ProductResponse> toDtoList(List<Product> products);
}
