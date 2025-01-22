package com.vault.lockedravault.service;

import com.vault.lockedravault.model.entity.Category;
import com.vault.lockedravault.model.entity.Domain;
import com.vault.lockedravault.model.entity.UserCredentialsForDomain;
import com.vault.lockedravault.repository.CredentialRepository;
import com.vault.lockedravault.security.jwt.JwtUtils;
import com.vault.lockedravault.security.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialRepository credentialRepository;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository, UserService userService, JwtUtils jwtUtils) {
        this.credentialRepository = credentialRepository;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    public List<UserCredentialsForDomain> getAllDataForDomain(String token) {
        UserEntity userEntity = getUserEntity(token);
        return credentialRepository.findAllByUserEntity(userEntity);
    }

    public UserCredentialsForDomain saveOrGetUserCredential(Category category, Domain domain, String passwordForDomain, String userNameForDomain, String token) {
            UserEntity userEntity = getUserEntity(token);
        try {
            UserCredentialsForDomain newUserData = new UserCredentialsForDomain(userEntity, category, domain, userNameForDomain, passwordForDomain);
            return credentialRepository.save(newUserData);
        } catch (Exception e) {
            // Catch the exception if the entity already saved in the DB
            return credentialRepository
                    .findByUserEntityAndDomainAndCategoryAndDomainPasswordAndDomainUserName(
                            userEntity,
                            domain,
                            category,
                            passwordForDomain,
                            userNameForDomain
                    ).orElseThrow();
        }
    }

    private UserEntity getUserEntity(String token) {
        String userName = jwtUtils.getUserNameForJwtToken(token);
        return userService.getUser(userName);
    }
}
