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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CredentialServiceTest {

    @Mock
    private CredentialRepository credentialRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private DomainRepository domainRepository;
    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private CredentialService credentialService;

    @Test
    void getAllUserCredentials_userNameExists() {

        String testUserName = "testUserName";
        String testEmail = "testEmail";
        String testPassword = "testPassword";
        String testCategoryName = "testCategoryName";
        String testDomainName = "testDomainName";
        String testDomainUrl = "testDomainUrl";
        String testDomainUserName = "testDomainUserName";
        String testDomainPassword = "testDomainPassword";
        String testToken = "testToken";

        Category testCategory= new Category(testCategoryName);
        Domain testDomain = new Domain(testDomainName, testDomainUrl);
        UserEntity testUserEntity = new UserEntity(testUserName, testEmail, testPassword);
        List<UserCredentialsForDomain> testCredentials = List.of(new UserCredentialsForDomain(
                testUserEntity,
                testCategory,
                testDomain,
                testDomainUserName,
                testDomainPassword));

        when(credentialRepository.findAllByUserEntityUserName(testUserName)).thenReturn(testCredentials);
        when(jwtUtils.getUserNameFromJwtToken(testToken)).thenReturn(testUserName);

        List<UserCredentialsForDomain> result = credentialService.getAllUserCredentials(testToken);

        assertEquals(testCredentials, result);

    }

    @Test
    void getAllUserCredentials_userNameDoesNotExists() {
        String testUserName = "testUserName";
        String testToken = "testToken";

        List<UserCredentialsForDomain> testCredentials = List.of();

        when(credentialRepository.findAllByUserEntityUserName(testUserName)).thenReturn(testCredentials);
        when(jwtUtils.getUserNameFromJwtToken(testToken)).thenReturn(testUserName);

        List<UserCredentialsForDomain> result = credentialService.getAllUserCredentials(testToken);

        assertEquals(testCredentials, result);
    }

    @Test
    void getUserCredentialsByCategory_categoryExists() {
        String testUserName = "testUserName";
        String testEmail = "testEmail";
        String testPassword = "testPassword";
        String testCategoryName = "testCategory";
        String testToken = "testToken";
        String testDomainName = "testDomainName";
        String testDomainUrl = "testDomainUrl";
        String testDomainUserName = "testDomainUserName";
        String testDomainPassword = "testDomainPassword";
        Domain testDomain = new Domain(testDomainName, testDomainUrl);
        Category testCategory = new Category(testCategoryName);
        UserEntity testUserEntity = new UserEntity(
                testUserName,
                testEmail,
                testPassword
        );

        List<UserCredentialsForDomain> testCredentials = List.of(new UserCredentialsForDomain(
                testUserEntity,
                testCategory,
                testDomain,
                testDomainUserName,
                testDomainPassword));

        when(jwtUtils.getUserNameFromJwtToken(testToken)).thenReturn(testUserName);
        when(credentialRepository.findByUserEntityUserNameAndCategoryCategoryName(testUserName, testCategoryName)).thenReturn(testCredentials);

        List<UserCredentialsForDomain> result = credentialService.getUserCredentialsByCategory(testToken, testCategoryName);

        assertEquals(testCredentials, result);
    }

    @Test
    void getUserCredentialsByCategory_userNameOrCategoryDoesNotExists() {
        String testUserName = "testUserName";
        String testCategoryName = "testCategory";
        String testToken = "testToken";

        List<UserCredentialsForDomain> testCredentials = List.of();

        when(jwtUtils.getUserNameFromJwtToken(testToken)).thenReturn(testUserName);
        when(credentialRepository.findByUserEntityUserNameAndCategoryCategoryName(testUserName, testCategoryName)).thenReturn(testCredentials);

        List<UserCredentialsForDomain> result = credentialService.getUserCredentialsByCategory(testToken, testCategoryName);

        assertEquals(testCredentials, result);
    }

    @Test
    void saveOrGetUserCredential_happyCase() {

        String testToken = "testToken";
        String testUserName = "testUserName";
        String testEmail = "testEmail";
        String testPassword = "testPassword";

        String testUserNameForDomain = "testUserNameForDomain";
        String testPasswordForDomain = "testPasswordForDomain";
        String testDomainName = "testDomainName";
        String testDomainUrl = "testDomainUrl";
        String testCategoryName = "testCategoryName";
        Category testCategory = new Category(testCategoryName);
        Domain testDomain = new Domain(testDomainName, testDomainUrl);

        NewUserDataRequest testUserData = new NewUserDataRequest(
                testDomainName,
                testUserNameForDomain,
                testPasswordForDomain,
                testDomainName,
                testDomainUrl,
                testCategoryName);

        UserEntity testUserEntity = new UserEntity(
                testUserName,
                testEmail,
                testPassword
        );

        UserCredentialsForDomain testCredentials = new UserCredentialsForDomain(
                testUserEntity,
                testCategory,
                testDomain,
                testUserNameForDomain,
                testPasswordForDomain);

        when(jwtUtils.getUserNameFromJwtToken(testToken)).thenReturn(testUserName);
        when(userRepository.getUserEntityByUserName(testUserName)).thenReturn(Optional.of(testUserEntity));
        when(domainRepository.findByDomainName(testDomainName)).thenReturn(Optional.empty());
        when(categoryRepository.findByCategoryName(testCategoryName)).thenReturn(Optional.empty());
        when(credentialRepository.save(Mockito.any())).thenReturn(testCredentials);

        UserCredentialsForDomain result = credentialService.saveOrGetUserCredential(testUserData, testToken);

        assertEquals(testCredentials, result);
    }
}