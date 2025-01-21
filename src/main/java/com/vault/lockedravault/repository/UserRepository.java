package com.vault.lockedravault.repository;

import com.vault.lockedravault.security.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> getUserEntityByUserName(String username);

}
