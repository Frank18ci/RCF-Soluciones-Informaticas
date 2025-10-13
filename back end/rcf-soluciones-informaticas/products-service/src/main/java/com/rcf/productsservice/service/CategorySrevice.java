package com.rcf.productsservice.service;

import com.rcf.productsservice.dto.CategoryRequest;
import com.rcf.productsservice.dto.CategoryResponse;

import java.util.List;

public interface CategorySrevice {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);
    void deleteCategory(Long id);

    List<CategoryResponse> searchCategoriesByName(String name);
}
