package com.rcf.productsservice.controller;

import com.rcf.productsservice.dto.ProductAttributeValueRequest;
import com.rcf.productsservice.service.ProductAttributeValueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-attribute-values")
public class ProductAttributeValueController {
    private final ProductAttributeValueService productAttributeValueService;
    @GetMapping
    public ResponseEntity<?> getAllProductAttributeValues() {
        return ResponseEntity.ok(productAttributeValueService.getAllProductAttributeValues());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductAttributeValueById(@PathVariable Long id) {
        return ResponseEntity.ok(productAttributeValueService.getProductAttributeValueById(id));
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductAttributeValuesByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(productAttributeValueService.getProductAttributeValuesByProductId(productId));
    }
    @PostMapping
    public ResponseEntity<?> createProductAttributeValue(@RequestBody @Valid ProductAttributeValueRequest productAttributeValueRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productAttributeValueService.createProductAttributeValue(productAttributeValueRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductAttributeValue(@PathVariable Long id, @RequestBody @Valid ProductAttributeValueRequest productAttributeValueRequest) {
        return ResponseEntity.ok(productAttributeValueService.updateProductAttributeValue(id,productAttributeValueRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductAttributeValue(@PathVariable Long id) {
        productAttributeValueService.deleteProductAttributeValue(id);
        return ResponseEntity.noContent().build();
    }
}
