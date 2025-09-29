package com.rcf.productsservice.controller;

import com.rcf.productsservice.dto.ProductAttributeRequest;
import com.rcf.productsservice.service.ProductAttributeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProductAttributeController {
    private final ProductAttributeService productAttributeService;

    @GetMapping
    public ResponseEntity<?> getAllProductAttributes() {
        return ResponseEntity.ok(productAttributeService.getAllProductAttributes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductAttributeById(@PathVariable Long id) {
        return ResponseEntity.ok(productAttributeService.getProductAttributeById(id));
    }
    @PostMapping
    public ResponseEntity<?> createProductAttribute(@RequestBody @Valid ProductAttributeRequest productAttributeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productAttributeService.createProductAttribute(productAttributeRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductAttribute(@PathVariable Long id, @RequestBody @Valid ProductAttributeRequest productAttributeRequest) {
        return ResponseEntity.ok(productAttributeService.updateProductAttribute(id, productAttributeRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductAttribute(@PathVariable Long id) {
        productAttributeService.deleteProductAttribute(id);
        return ResponseEntity.noContent().build();
    }
}
