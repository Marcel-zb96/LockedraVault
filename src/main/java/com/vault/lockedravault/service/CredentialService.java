package com.vault.lockedravault.service;

import com.vault.lockedravault.model.NewUserDataRequest;
import com.vault.lockedravault.model.entity.Category;
import com.vault.lockedravault.model.entity.Domain;
import com.vault.lockedravault.model.entity.UserCredentialsForDomain;
import com.vault.lockedravault.repository.CategoryRepository;
import com.vault.lockedravault.repository.CredentialRepository;
import com.vault.lockedravault.repository.DomainRepository;
import com.vault.lockedravault.repository.UserRepository;
import com.vault.lockedravault.security.jwt.JwtUtils;
import com.vault.lockedravault.security.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CredentialService {

    private final CredentialRepository credentialRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final DomainRepository domainRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository, UserRepository userRepository, CategoryRepository categoryRepository, DomainRepository domainRepository, JwtUtils jwtUtils) {
        this.credentialRepository = credentialRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.domainRepository = domainRepository;
        this.jwtUtils = jwtUtils;
    }

    public List<UserCredentialsForDomain> getAllUserCredentials(String token) {
        String userName = jwtUtils.getUserNameFromJwtToken(token);
        return credentialRepository.findAllByUserEntityUserName(userName);
    }

    public List<UserCredentialsForDomain> getUserCredentialsByCategory(String token, String category) {
        String userName = jwtUtils.getUserNameFromJwtToken(token);
        return credentialRepository.findByUserEntityUserNameAndCategoryCategoryName(userName, category);
    }

    public UserCredentialsForDomain saveOrGetUserCredential(NewUserDataRequest userData, String token) {
            UserEntity userEntity = getUserEntity(token);
            Domain domain = getDomain(userData.domainName(), userData.domainUrl());
            Category category = getCategory(userData.categoryName());

            UserCredentialsForDomain newUserData = new UserCredentialsForDomain(userEntity, category, domain, userData.userNameForDomain(), userData.passwordForDomain());
            return credentialRepository.save(newUserData);
    }

    private UserEntity getUserEntity(String token) {
        String userName = jwtUtils.getUserNameFromJwtToken(token);
        return userRepository.getUserEntityByUserName(userName).get();
    }

    private Domain getDomain(String domainName, String domainUrl) {
        Optional<Domain> domain = domainRepository.findByDomainName(domainName);
        return domain.orElseGet(() -> new Domain(domainName, domainUrl));
    }

    private Category getCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);
        return category.orElseGet(() -> new Category(categoryName));
    }
}

