package com.vault.lockedravault.repository;

import com.vault.lockedravault.model.entity.UserCredentialsForDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<UserCredentialsForDomain, UUID> {
    List<UserCredentialsForDomain> findAllByUserEntityUserName(String userName);
    List<UserCredentialsForDomain>  findByUserEntityUserNameAndCategoryCategoryName(String userEntity_userName, String category_categoryName);
}
