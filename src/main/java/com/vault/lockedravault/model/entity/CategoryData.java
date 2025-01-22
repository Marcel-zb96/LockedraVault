package com.vault.lockedravault.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class CategoryData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String categoryName;

    public CategoryData(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryData() {
    }

    public UUID getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
