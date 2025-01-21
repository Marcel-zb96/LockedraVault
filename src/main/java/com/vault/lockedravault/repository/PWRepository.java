package com.vault.lockedravault.repository;

import com.vault.lockedravault.model.entity.UserDataForDomain;
import com.vault.lockedravault.security.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PWRepository extends JpaRepository<UserDataForDomain, Long> {
    List<UserDataForDomain> findByUserEntity(UserEntity userEntity);
}
