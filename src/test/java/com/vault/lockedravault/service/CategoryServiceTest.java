package com.vault.lockedravault.service;

import com.vault.lockedravault.model.entity.Category;
import com.vault.lockedravault.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllCategory_happyCase() {
        List<Category> categories = List.of(new Category("test-category-name"));

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategory();

        assertEquals(categories, result);
    }

    @Test
    void saveOrGetCategory_happyCase() {
        String testCategoryName = "test_category";
        Category testCategory = new Category(testCategoryName);

        Mockito.when(categoryRepository.findByCategoryName(testCategoryName)).thenReturn(Optional.of(testCategory));

        Category result = categoryService.saveOrGetCategory(testCategoryName);

        assertEquals(testCategory, result);

        verify(categoryRepository, never()).save(Mockito.any());
    }

    @Test
    void saveOrGetCategory_noNameParameter() {
        String testCategoryName = "test_category";
        Category testCategory = new Category(testCategoryName);

        Mockito.when(categoryRepository.findByCategoryName(testCategoryName)).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(testCategory);

        Category result = categoryService.saveOrGetCategory(testCategoryName);

        assertEquals(testCategory, result);

        verify(categoryRepository, times(1)).findByCategoryName(testCategoryName);
        verify(categoryRepository, times(1)).save(Mockito.any());
    }
}