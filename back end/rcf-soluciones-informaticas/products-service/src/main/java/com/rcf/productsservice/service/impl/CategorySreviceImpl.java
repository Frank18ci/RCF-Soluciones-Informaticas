package com.rcf.productsservice.service.impl;

import com.rcf.productsservice.dto.CategoryRequest;
import com.rcf.productsservice.dto.CategoryResponse;
import com.rcf.productsservice.exception.ResourceNotFound;
import com.rcf.productsservice.model.Category;
import com.rcf.productsservice.repository.CategoryRepository;
import com.rcf.productsservice.service.CategorySrevice;
import com.rcf.productsservice.util.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorySreviceImpl implements CategorySrevice {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(
        () -> new ResourceNotFound("Category type not found with id: " + id)
        ));
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toEntity(categoryRequest);
        if(categoryRequest.parentId() == null || categoryRequest.parentId() == 0) {
            category.setParent(null);
        }
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category categoryFound = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Category not found with id: " + id)
        );
        categoryFound.setName(categoryRequest.name());
        categoryFound.setSlug(categoryRequest.slug());
        if(categoryRequest.parentId() == null || categoryRequest.parentId() == 0) {
            categoryFound.setParent(null);
        }
        if(categoryRequest.parentId() != null && categoryRequest.parentId() > 0) {
            categoryFound.setParent(categoryMapper.toEntity(categoryRequest).getParent());
        }

        return categoryMapper.toDto(categoryRepository.save(categoryFound));
    }

    @Override
    public void deleteCategory(Long id) {
        Category categoryFound = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Category type not found with id: " + id)
        );
        categoryRepository.delete(categoryFound);
    }

    @Override
    public List<CategoryResponse> searchCategoriesByName(String name) {
        return categoryMapper.toDtoList(categoryRepository.findByNameContaining(name));
    }
}
