package com.rcf.productsservice.service;

import com.rcf.productsservice.dto.ProductRequest;
import com.rcf.productsservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse createProduct(ProductRequest productRequest) throws Exception;

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);

    List<ProductResponse> searchProducts(String name, Long categoryId, Long minPrice, Long maxPrice);
}
