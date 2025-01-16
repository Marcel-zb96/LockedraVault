package com.vault.lockedravault.repository;

import com.vault.lockedravault.model.UserDataForDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PWRepository extends JpaRepository<UserDataForDomain, Long> {
}
