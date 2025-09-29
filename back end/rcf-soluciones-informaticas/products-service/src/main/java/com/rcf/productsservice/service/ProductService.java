package com.rcf.productsservice.service;

import com.rcf.productsservice.dto.ProductRequest;
import com.rcf.productsservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List <ProductResponse> getAllProducts();
    ProductResponse getProductById (Long id);
    ProductResponse createProduct (ProductRequest productRequest);
    ProductResponse updateProduct (Long id, ProductRequest productRequest);
    void deleteProduct (Long id);
}
