package com.rcf.servicesservice.service.impl;

import com.rcf.servicesservice.client.ProductClient;
import com.rcf.servicesservice.dto.ProductServiceRequest;
import com.rcf.servicesservice.dto.ProductServiceResponse;
import com.rcf.servicesservice.exception.ResourceNotFound;
import com.rcf.servicesservice.model.ProductService;
import com.rcf.servicesservice.repository.ProductServiceRepository;
import com.rcf.servicesservice.service.ProductServiceService;
import com.rcf.servicesservice.util.ProductServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceServiceImpl implements ProductServiceService {
    private final ProductServiceRepository productServiceRepository;
    private final ProductServiceMapper productServiceMapper;

    private final ProductClient productClient;

    @Override
    public List<ProductServiceResponse> getAllProductServices() {
        return productServiceMapper.toDtoList(productServiceRepository.findAll());
    }

    @Override
    public ProductServiceResponse getProductServiceById(Long id) {
        return productServiceMapper.toDto(productServiceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product/Service not found with id: " + id)
        ));
    }

    @Override
    public ProductServiceResponse saveProductService(ProductServiceRequest productServiceRequest) {
        productClient.getProductById(productServiceRequest.productId());
        return productServiceMapper.toDto(productServiceRepository.save(
                productServiceMapper.toEntity(productServiceRequest)
        ));
    }

    @Override
    public ProductServiceResponse updateProductService(Long id, ProductServiceRequest productServiceRequest) {
        ProductService productServiceFound = productServiceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product/Service not found with id: " + id)
        );

        productClient.getProductById(productServiceRequest.productId());

        productServiceFound.setServiceId(productServiceRequest.serviceId());
        productServiceFound.setProductId(productServiceRequest.productId());
        productServiceFound.setPriceOverrideCents(productServiceRequest.priceOverrideCents());

        return productServiceMapper.toDto(productServiceRepository.save(productServiceFound));
    }

    @Override
    public void deleteProductService(Long id) {
        ProductService productServiceFound = productServiceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product/Service not found with id: " + id)
        );
        productServiceRepository.delete(productServiceFound);
    }
}
