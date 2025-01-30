package com.vault.lockedravault.controller;

import com.vault.lockedravault.model.NewCategoryRequest;
import com.vault.lockedravault.model.entity.Category;
import com.vault.lockedravault.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/new")
    public Category saveNewCategory(@RequestBody NewCategoryRequest newCategory) {
        return categoryService.saveOrGetCategory(newCategory.categoryName());
    }
}
