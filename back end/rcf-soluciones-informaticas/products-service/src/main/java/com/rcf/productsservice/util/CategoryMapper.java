package com.rcf.productsservice.util;

import com.rcf.productsservice.dto.CategoryRequest;
import com.rcf.productsservice.dto.CategoryResponse;
import com.rcf.productsservice.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "parentId", target = "parent.id")
    Category toEntity(CategoryRequest categoryRequest);
    CategoryResponse toDto(Category category);
    List<CategoryResponse> toDtoList(List<Category> categories);
}
