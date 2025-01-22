package com.vault.lockedravault.controller;

import com.vault.lockedravault.model.NewUserDataRequest;
import com.vault.lockedravault.model.entity.Category;
import com.vault.lockedravault.model.entity.Domain;
import com.vault.lockedravault.model.entity.UserCredentialsForDomain;
import com.vault.lockedravault.service.CategoryService;
import com.vault.lockedravault.service.DomainService;
import com.vault.lockedravault.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CredentialController {

    private final CredentialService credentialService;
    private final CategoryService categoryService;
    private final DomainService domainService;

    @Autowired
    public CredentialController(CredentialService credentialService, CategoryService categoryService, DomainService domainService) {
        this.credentialService = credentialService;
        this.categoryService = categoryService;
        this.domainService = domainService;
    }

    @GetMapping("/data/all")
    public List<UserCredentialsForDomain> getUserDataForDomain(@CookieValue("accessToken") String token) {
        return credentialService.getAllDataForDomain(token);
    }

    @PostMapping("/save")
    public UserCredentialsForDomain saveUserDataForDomain(@RequestBody NewUserDataRequest userData, @CookieValue("accessToken") String token) {
        Category category = categoryService.saveOrGetCategory(userData.categoryName());
        Domain domain = domainService.saveOrGetDomain(userData.domainName(), userData.domainUrl());
        return credentialService.saveOrGetUserCredential(
                category,
                domain,
                userData.passwordForDomain(),
                userData.userNameForDomain(),
                token);
    }
}
