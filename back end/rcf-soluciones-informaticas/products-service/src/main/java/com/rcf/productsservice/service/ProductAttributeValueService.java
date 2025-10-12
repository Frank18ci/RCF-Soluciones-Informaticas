package com.rcf.productsservice.service;

import com.rcf.productsservice.dto.ProductAttributeValueRequest;
import com.rcf.productsservice.dto.ProductAttributeValueResponse;

import java.util.List;

public interface ProductAttributeValueService {
    List<ProductAttributeValueResponse> getAllProductAttributeValues();
    ProductAttributeValueResponse getProductAttributeValueById(Long id);
    ProductAttributeValueResponse createProductAttributeValue(ProductAttributeValueRequest request);
    ProductAttributeValueResponse updateProductAttributeValue(Long id, ProductAttributeValueRequest request);
    void deleteProductAttributeValue(Long id);

    List<ProductAttributeValueResponse> getProductAttributeValuesByProductId(Long productId);
}
