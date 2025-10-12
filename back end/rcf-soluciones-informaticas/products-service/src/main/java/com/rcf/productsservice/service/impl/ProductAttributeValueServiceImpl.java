package com.rcf.productsservice.service.impl;


import com.rcf.productsservice.dto.ProductAttributeValueRequest;
import com.rcf.productsservice.dto.ProductAttributeValueResponse;
import com.rcf.productsservice.exception.ResourceNotFound;
import com.rcf.productsservice.model.ProductAttributeValue;
import com.rcf.productsservice.repository.ProductAttributeValueRepository;
import com.rcf.productsservice.service.ProductAttributeValueService;
import com.rcf.productsservice.util.ProductAttributeValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {
    private final ProductAttributeValueRepository productAttributeValueRepository;
    private final ProductAttributeValueMapper productAttributeValueMapper;

    @Override
    public List<ProductAttributeValueResponse> getAllProductAttributeValues() {
        return productAttributeValueMapper.toDtoList(productAttributeValueRepository.findAll());
    }

    @Override
    public ProductAttributeValueResponse getProductAttributeValueById(Long id) {
        return productAttributeValueMapper.toDto(productAttributeValueRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product attribute value not found with id: " + id
        )));
    }

    @Override
    public ProductAttributeValueResponse createProductAttributeValue(ProductAttributeValueRequest request) {
        return productAttributeValueMapper.toDto(
                productAttributeValueRepository.save(
                        productAttributeValueMapper.toEntity(request)
                )
        );
    }

    @Override
    public ProductAttributeValueResponse updateProductAttributeValue(Long id, ProductAttributeValueRequest request) {
        ProductAttributeValue productAttributeValueFound = productAttributeValueRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product attribute value not found with id: " + id)
        );
        productAttributeValueFound.setProduct(productAttributeValueMapper.toEntity(request).getProduct());
        productAttributeValueFound.setValue(request.value());
        productAttributeValueFound.setProductAttribute(productAttributeValueMapper.toEntity(request).getProductAttribute());

        return productAttributeValueMapper.toDto(
                productAttributeValueRepository.save(productAttributeValueFound)
        );
    }

    @Override
    public void deleteProductAttributeValue(Long id) {
        ProductAttributeValue productAttributeValueFound = productAttributeValueRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product attribute value not found with id: " + id)
        );
        productAttributeValueRepository.delete(productAttributeValueFound);
    }

    @Override
    public List<ProductAttributeValueResponse> getProductAttributeValuesByProductId(Long productId) {
        return productAttributeValueMapper.toDtoList(productAttributeValueRepository.findByProductId(productId));
    }
}
