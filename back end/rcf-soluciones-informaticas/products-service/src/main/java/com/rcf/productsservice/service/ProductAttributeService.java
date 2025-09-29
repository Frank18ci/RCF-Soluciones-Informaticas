package com.rcf.productsservice.service;

import com.rcf.productsservice.dto.ProductAttributeRequest;
import com.rcf.productsservice.dto.ProductAttributeResponse;

import java.util.List;
public interface ProductAttributeService {
    List <ProductAttributeResponse> getAllProductAttributes();
    ProductAttributeResponse getProductAttributeById(Long id);
    ProductAttributeResponse createProductAttribute(ProductAttributeRequest productAttributeRequest);
    ProductAttributeResponse updateProductAttribute(Long id, ProductAttributeRequest productAttributeRequest);
    void deleteProductAttribute(Long id);
}
