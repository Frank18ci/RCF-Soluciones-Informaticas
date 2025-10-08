package com.rcf.productsservice.service.impl;

import com.rcf.productsservice.dto.ProductRequest;
import com.rcf.productsservice.dto.ProductResponse;
import com.rcf.productsservice.exception.ResourceNotFound;
import com.rcf.productsservice.model.Product;
import com.rcf.productsservice.repository.ProductRepository;
import com.rcf.productsservice.service.ProductService;
import com.rcf.productsservice.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product not found with id: " + id)
        ));
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        if(productRequest.discountId() != null && productRequest.discountId() > 0) {
            product.setDiscount(productMapper.toEntity(productRequest).getDiscount());
        } else {
            product.setDiscount(null);
        }
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product productFound = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product not found with id: " + id)
        );
        productFound.setSku(productRequest.sku());
        productFound.setName(productRequest.name());
        productFound.setShortDescription(productRequest.shortDescription());
        productFound.setDescription(productRequest.description());
        productFound.setBasePriceCents(productRequest.basePriceCents());
        productFound.setPurchasePriceCents(productRequest.purchasePriceCents());
        productFound.setSalePriceCents(productRequest.salePriceCents());
        productFound.setTaxRate(productRequest.taxRate());
        productFound.setStock(productRequest.stock());
        productFound.setCategory(productMapper.toEntity(productRequest).getCategory());
        if(productRequest.discountId() != null && productRequest.discountId() > 0) {
            productFound.setDiscount(productMapper.toEntity(productRequest).getDiscount());
        } else {
            productFound.setDiscount(null);
        }
        productFound.setActive(productRequest.active());
        return productMapper.toDto(productRepository.save(productFound));
    }

    @Override
    public void deleteProduct(Long id) {
        Product productFound = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product not found with id: " + id)
        );
        productRepository.delete(productFound);
    }
}
