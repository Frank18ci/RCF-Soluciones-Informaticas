package com.rcf.servicesservice.service;

import com.rcf.servicesservice.dto.ProductServiceRequest;
import com.rcf.servicesservice.dto.ProductServiceResponse;

import java.util.List;

public interface ProductServiceService {
    List<ProductServiceResponse> getAllProductServices();
    ProductServiceResponse getProductServiceById(Long id);
    ProductServiceResponse saveProductService(ProductServiceRequest productServiceRequest);
    ProductServiceResponse updateProductService(Long id, ProductServiceRequest productServiceRequest);
    void deleteProductService(Long id);
}
