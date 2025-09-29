package com.rcf.productsservice.service.impl;

import com.rcf.productsservice.dto.ProductAttributeRequest;
import com.rcf.productsservice.dto.ProductAttributeResponse;
import com.rcf.productsservice.model.ProductAttribute;
import com.rcf.productsservice.repository.ProductAttributeRepository;
import com.rcf.productsservice.service.ProductAttributeService;
import com.rcf.productsservice.util.ProductAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductAttributeServiceImpl implements ProductAttributeService {
    private final ProductAttributeRepository productAttributeRepository;
    private final ProductAttributeMapper productAttributeMapper;
    @Override
    public List<ProductAttributeResponse> getAllProductAttributes() {
        return  productAttributeMapper.toDtoList(productAttributeRepository.findAll());
    }

    @Override
    public ProductAttributeResponse getProductAttributeById(Long id) {
        return productAttributeMapper.toDto(productAttributeRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Attribute not found")));
    }

    @Override
    public ProductAttributeResponse createProductAttribute(ProductAttributeRequest productAttributeRequest) {
        return productAttributeMapper.toDto(productAttributeRepository.save(productAttributeMapper.toEntity(productAttributeRequest)));
    }

    @Override
    public ProductAttributeResponse updateProductAttribute(Long id, ProductAttributeRequest productAttributeRequest) {
        ProductAttribute productFound = productAttributeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product Attribute not found"+id));
        productFound.setName(productAttributeRequest.name());
        return productAttributeMapper.toDto(productAttributeRepository.save(productFound));
    }

    @Override
    public void deleteProductAttribute(Long id) {
        ProductAttribute productFound = productAttributeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product Attribute not found"+id));
        productAttributeRepository.delete(productFound);
    }
}
