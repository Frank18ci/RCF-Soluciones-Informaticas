package com.rcf.productsservice.controller;

import com.rcf.productsservice.dto.DiscountTypeRequest;
import com.rcf.productsservice.service.DiscountTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discount-types")
public class DiscountTypeController {
    private final DiscountTypeService discountTypeService;
    @GetMapping
    public ResponseEntity<?> getAllDiscountTypes(){
        return ResponseEntity.ok(discountTypeService.getAllDiscountTypes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscountTypeById(@PathVariable Long id){
        return ResponseEntity.ok(discountTypeService.getDiscountTypeById(id));
    }
    @PostMapping
    public ResponseEntity<?> saveDiscountType(@RequestBody @Valid DiscountTypeRequest discountTypeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(discountTypeService.saveDiscountType(discountTypeRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscountType(@PathVariable Long id, @RequestBody @Valid DiscountTypeRequest discountTypeRequest){
        return ResponseEntity.ok(discountTypeService.updateDiscountType(id, discountTypeRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscountType(@PathVariable Long id) {
        discountTypeService.deleteDiscountType(id);
        return ResponseEntity.noContent().build();
    }

}
