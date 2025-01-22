package com.vault.lockedravault.repository;

import com.vault.lockedravault.model.entity.CategoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryData, Long> {
}
