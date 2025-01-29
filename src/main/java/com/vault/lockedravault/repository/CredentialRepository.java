package com.vault.lockedravault.repository;

import com.vault.lockedravault.model.entity.Category;
import com.vault.lockedravault.model.entity.Domain;
import com.vault.lockedravault.model.entity.UserCredentialsForDomain;
import com.vault.lockedravault.security.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<UserCredentialsForDomain, Long> {
    List<UserCredentialsForDomain> findAllByUserEntityUserName(String userName);
    List<UserCredentialsForDomain>  findByUserEntityUserNameAndCategoryCategoryName(String userEntity_userName, String category_categoryName);
}
