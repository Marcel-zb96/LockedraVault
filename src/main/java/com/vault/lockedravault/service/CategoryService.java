package com.vault.lockedravault.service;

import com.vault.lockedravault.model.entity.Category;
import com.vault.lockedravault.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryService) {
        this.categoryRepository = categoryService;
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category saveOrGetCategory(String categoryName) {
        try {
            Category newCategory = new Category(categoryName);
            return categoryRepository.save(newCategory);
        } catch (Exception e) {
            // Catch the exception if the entity already saved in the DB
            return categoryRepository.findByCategoryName(categoryName).orElseThrow();
        }
    }
}
