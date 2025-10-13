package com.rcf.productsservice.service.impl;

import com.rcf.productsservice.dto.ProductRequest;
import com.rcf.productsservice.dto.ProductResponse;
import com.rcf.productsservice.exception.ResourceNotFound;
import com.rcf.productsservice.model.Product;
import com.rcf.productsservice.repository.ProductRepository;
import com.rcf.productsservice.service.ProductService;
import com.rcf.productsservice.storage.ImageStorageService;
import com.rcf.productsservice.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ImageStorageService imageStorageService;

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
        Product product = productMapper.toEntity(productRequest, productRequest.sku());

        if(productRequest.discountId() != null && productRequest.discountId() > 0) {
            product.setDiscount(productMapper.toEntity(productRequest, productRequest.sku()).getDiscount());
        } else {
            product.setDiscount(null);
        }

        try{
            imageStorageService.saveImage(productRequest.image(), product.getSku());
        } catch (Exception e) {
            throw new RuntimeException("Could not store the image. Error: " + e.getMessage());
        }

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product productConverter = productMapper.toEntity(productRequest, productRequest.sku());
        Product productFound = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product not found with id: " + id)
        );
        productFound.setSku(productRequest.sku());
        productFound.setName(productRequest.name());
        productFound.setShortDescription(productRequest.shortDescription());
        productFound.setImgUrl(productConverter.getImgUrl());
        productFound.setDescription(productRequest.description());
        productFound.setBasePriceCents(productRequest.basePriceCents());
        productFound.setPurchasePriceCents(productRequest.purchasePriceCents());
        productFound.setSalePriceCents(productRequest.salePriceCents());
        productFound.setTaxRate(productRequest.taxRate());
        productFound.setStock(productRequest.stock());
        productFound.setCategory(productConverter.getCategory());

        if(productRequest.discountId() != null && productRequest.discountId() > 0) {
            productFound.setDiscount(productConverter.getDiscount());
        } else {
            productFound.setDiscount(null);
        }

        try{
            imageStorageService.saveImage(productRequest.image(), productFound.getSku());
        } catch (Exception e) {
            throw new RuntimeException("Could not store the image. Error: " + e.getMessage());
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

    @Override
    public List<ProductResponse> searchProducts(String name, Long categoryId, Long minPrice, Long maxPrice) {
        List<ProductResponse> products;
        if(categoryId == 0){
            products = productMapper.toDtoList(productRepository.findByNameContainingAndSalePriceCentsBetween(name, minPrice, maxPrice));
        } else {
            products = productMapper.toDtoList(productRepository.findByNameContainingAndCategory_IdAndSalePriceCentsBetween(name, categoryId, minPrice, maxPrice));
        }
        return products;
    }

    @Override
    public List<ProductResponse> searchProducts2(String name) {
        return productMapper.toDtoList(productRepository.findByNameContaining(name));
    }
}
