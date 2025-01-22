package com.vault.lockedravault.service;

import com.vault.lockedravault.model.entity.CategoryData;
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

    public List<CategoryData> getAllCategory() {
        return categoryRepository.findAll();
    }
}
