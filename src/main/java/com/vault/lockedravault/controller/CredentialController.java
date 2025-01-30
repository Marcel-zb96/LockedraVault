package com.vault.lockedravault.controller;

import com.vault.lockedravault.model.NewUserDataRequest;
import com.vault.lockedravault.model.entity.UserCredentialsForDomain;
import com.vault.lockedravault.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credential")
public class CredentialController {

    private final CredentialService credentialService;

    @Autowired
    public CredentialController(
            CredentialService credentialService
    ) {
        this.credentialService = credentialService;
    }

    @GetMapping("/all")
    public List<UserCredentialsForDomain> getAllUserCredentials(@CookieValue("accessToken") String token) {
        return credentialService.getAllUserCredentials(token);
    }

    @GetMapping("/{category}")
    public List<UserCredentialsForDomain> getUserCredentialsByCategory(@CookieValue("accessToken") String token, @PathVariable String category) {
        return credentialService.getUserCredentialsByCategory(token, category);
    }

    @PostMapping("/save")
    public UserCredentialsForDomain saveUserDataForDomain(@RequestBody NewUserDataRequest userData, @CookieValue("accessToken") String token) {
        return credentialService.saveOrGetUserCredential(userData, token);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCredential(@PathVariable String id) {
        credentialService.deleteCredential(id);
        return ResponseEntity.ok().build();
    }
}
