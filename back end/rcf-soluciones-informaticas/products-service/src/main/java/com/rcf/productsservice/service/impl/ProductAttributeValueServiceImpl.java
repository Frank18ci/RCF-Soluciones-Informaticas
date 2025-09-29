package com.rcf.productsservice.service.impl;


import com.rcf.productsservice.dto.ProductAttributeValueRequest;
import com.rcf.productsservice.dto.ProductAttributeValueResponse;
import com.rcf.productsservice.service.ProductAttributeValueService;

import java.util.List;

public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {

    @Override
    public List<ProductAttributeValueResponse> getAllProductAttributeValues() {
        return List.of();
    }

    @Override
    public ProductAttributeValueResponse getProductAttributeValueById(Long id) {
        return null;
    }

    @Override
    public ProductAttributeValueResponse createProductAttributeValue(ProductAttributeValueRequest request) {
        return null;
    }

    @Override
    public ProductAttributeValueResponse updateProductAttributeValue(Long id, ProductAttributeValueRequest request) {
        return null;
    }

    @Override
    public void deleteProductAttributeValue(Long id) {

    }
}
