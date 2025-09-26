package com.rcf.servicesservice.util;

import com.rcf.servicesservice.dto.ProductServiceRequest;
import com.rcf.servicesservice.dto.ProductServiceResponse;
import com.rcf.servicesservice.model.ProductService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductServiceMapper {
    ProductService toEntity(ProductServiceRequest productServiceRequest);
    ProductServiceResponse toDto(ProductService productService);
    List<ProductServiceResponse> toDtoList(List<ProductService> productServices);
}
