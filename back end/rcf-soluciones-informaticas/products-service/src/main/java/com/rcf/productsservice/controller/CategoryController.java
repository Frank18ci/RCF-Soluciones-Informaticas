package com.rcf.productsservice.controller;

import com.rcf.productsservice.dto.CategoryRequest;
import com.rcf.productsservice.service.CategorySrevice;
import feign.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategorySrevice categorySrevice;
    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(categorySrevice.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categorySrevice.getCategoryById(id));
    }
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody com.rcf.productsservice.dto.CategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySrevice.createCategory(categoryRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categorySrevice.updateCategory(id, categoryRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categorySrevice.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
