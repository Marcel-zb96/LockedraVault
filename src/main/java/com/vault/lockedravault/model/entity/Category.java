package com.vault.lockedravault.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public UUID getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
