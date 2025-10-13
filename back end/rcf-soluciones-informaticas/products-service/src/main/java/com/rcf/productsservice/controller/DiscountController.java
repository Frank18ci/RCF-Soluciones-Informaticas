package com.rcf.productsservice.controller;

import com.rcf.productsservice.dto.DiscountRequest;
import com.rcf.productsservice.service.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discounts")
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<?> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscountById(@PathVariable Long id) {
        return ResponseEntity.ok(discountService.getDiscountById(id));
    }
    @PostMapping
    public ResponseEntity<?> createDiscount(@RequestBody @Valid DiscountRequest discountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(discountService.createDiscount(discountRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable Long id, @RequestBody @Valid DiscountRequest discountRequest) {
        return ResponseEntity.ok(discountService.updateDiscount(id, discountRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchDiscounts(@RequestParam String code) {
        return ResponseEntity.ok(discountService.searchDiscountsByCode(code));
    }
}
