package com.rcf.productsservice.controller;

import com.rcf.productsservice.dto.ProductRequest;
import com.rcf.productsservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(id,productRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
