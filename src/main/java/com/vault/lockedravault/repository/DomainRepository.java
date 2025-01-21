package com.vault.lockedravault.repository;

import com.vault.lockedravault.model.entity.DomainData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<DomainData, Long> {
}
