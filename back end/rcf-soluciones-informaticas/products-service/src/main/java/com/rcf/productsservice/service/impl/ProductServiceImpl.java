package com.rcf.productsservice.service.impl;

import com.rcf.productsservice.dto.ProductRequest;
import com.rcf.productsservice.dto.ProductResponse;
import com.rcf.productsservice.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductResponse> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return null;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
