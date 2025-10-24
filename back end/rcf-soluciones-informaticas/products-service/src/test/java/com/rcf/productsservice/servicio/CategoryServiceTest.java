package com.rcf.productsservice.servicio;

import com.rcf.productsservice.dto.CategoryResponse;
import com.rcf.productsservice.model.Category;
import com.rcf.productsservice.repository.CategoryRepository;
import com.rcf.productsservice.service.impl.CategorySreviceImpl;
import com.rcf.productsservice.util.CategoryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategorySreviceImpl categoryServiceImpl;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;

    @Test
    @DisplayName("Debe retornar todas las categorias")
    void testGetAllCategories() {
        LocalDateTime created1 = LocalDateTime.of(2023, 1, 1, 10, 0);
        LocalDateTime updated1 = LocalDateTime.of(2023, 1, 2, 10, 0);
        LocalDateTime created2 = LocalDateTime.of(2023, 2, 1, 10, 0);
        LocalDateTime updated2 = LocalDateTime.of(2023, 2, 2, 10, 0);
        LocalDateTime created3 = LocalDateTime.of(2023, 3, 1, 10, 0);
        LocalDateTime updated3 = LocalDateTime.of(2023, 3, 2, 10, 0);

        List<CategoryResponse> categoryResponses = List.of(
                CategoryResponse.builder().id(1L).parent(null).name("Electronica").slug("electronica").createdAt(created1).updatedAt(updated1).build(),
                CategoryResponse.builder().id(2L).parent(CategoryResponse.builder().id(1L).name("Electronica")
                                .build()).name("Libros").slug("libros").createdAt(created2).updatedAt(updated2).build(),
                CategoryResponse.builder().id(3L).parent(null).name("Cocina").slug("cocina").createdAt(created3).updatedAt(updated3).build()
        );
        List<Category> categories = List.of(
                Category.builder().id(1L).parent(null).name("Electronica").slug("electronica").createdAt(created1)
                        .updatedAt(updated1).build(),
                Category.builder().id(2L).parent(Category.builder().id(1L).name("Electronica").build()).name("Libros").slug("libros").createdAt(created2).updatedAt(updated2).build(),
                Category.builder().id(3L).parent(null).name("Cocina").slug("cocina").createdAt(created3).updatedAt(updated3).build()
        );
        when(categoryRepository.findAll()).thenReturn(categories);
        when(categoryMapper.toDtoList(categories)).thenReturn(categoryResponses);
        List<CategoryResponse> categoryResponseList = categoryServiceImpl.getAllCategories();
        assertAll(
                () -> assertNotNull(categoryResponseList),
                () -> assertEquals(3, categoryResponseList.size()),
                () -> assertEquals("Electronica", categoryResponseList.getFirst().name()),
                () -> assertEquals("Libros", categoryResponseList.get(1).name()),
                () -> assertEquals("Cocina", categoryResponseList.get(2).name())
        );
        verify(categoryRepository).findAll();
        verify(categoryMapper).toDtoList(categories);
    }

}
