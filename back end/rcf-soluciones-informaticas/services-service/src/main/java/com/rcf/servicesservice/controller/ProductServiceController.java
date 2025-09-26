package com.rcf.servicesservice.controller;

import com.rcf.servicesservice.dto.ProductServiceRequest;
import com.rcf.servicesservice.service.ProductServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products-services")
public class ProductServiceController {
    private final ProductServiceService productServiceService;

    @GetMapping
    public ResponseEntity<?> getAllProductsServices(){
        return ResponseEntity.ok(productServiceService.getAllProductServices());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductServiceById(@PathVariable Long id){
        return ResponseEntity.ok(productServiceService.getProductServiceById(id));
    }
    @PostMapping
    public ResponseEntity<?> createProductService(@RequestBody @Valid ProductServiceRequest productServiceRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productServiceService.saveProductService(productServiceRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductService(@PathVariable Long id, @RequestBody @Valid ProductServiceRequest productServiceRequest){
        return ResponseEntity.ok(productServiceService.updateProductService(id, productServiceRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductService(@PathVariable Long id) {
        productServiceService.deleteProductService(id);
        return ResponseEntity.noContent().build();
    }
}
