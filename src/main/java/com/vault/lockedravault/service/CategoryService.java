package com.vault.lockedravault.service;

import com.vault.lockedravault.model.entity.Category;
import com.vault.lockedravault.repository.CategoryRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Category saveOrGetCategory(String categoryName) {
            return categoryRepository.findByCategoryName(categoryName)
                    .orElseGet(() -> categoryRepository.save(new Category(categoryName)));
    }
}
