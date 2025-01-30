package com.vault.lockedravault.service;

import com.vault.lockedravault.repository.UserRepository;
import com.vault.lockedravault.security.model.SignUpRequest;
import com.vault.lockedravault.security.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_happyCase() {
        String testPassword = "testPassword";
        String testEmail = "testEmail";
        String testUserName = "testUserName";

        SignUpRequest testSignUpRequest = new SignUpRequest(testUserName, testEmail, testPassword);

        when(encoder.encode(testPassword)).thenReturn(testPassword);

        userService.createUser(testSignUpRequest);

        ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);

        verify(userRepository, times(1)).save(userEntityCaptor.capture());

        UserEntity capturedUser = userEntityCaptor.getValue();

        assertEquals(testUserName, capturedUser.getUserName());
        assertEquals(testEmail, capturedUser.getEmail());
        assertEquals(testPassword, capturedUser.getPassword());
    }

    @Test
    void getUserEntity_userNameExists() {
        String testUserName = "testUserName";
        String testEmail = "testEmail";
        String testPassword = "testPassword";

        UserEntity testUserEntity = new UserEntity(testUserName, testEmail, testPassword);

        when(userRepository.getUserEntityByUserName(testUserName)).thenReturn(Optional.of(testUserEntity));

        Optional<UserEntity> result = userService.getUserEntity(testUserName);

        assertTrue(result.isPresent());
        assertThat(result).hasValue(testUserEntity);
    }

    @Test
    void getUserEntity_userNameDoesNotExists() {
        String testUserName = "testUserName";

        when(userRepository.getUserEntityByUserName(testUserName)).thenReturn(Optional.empty());

        Optional<UserEntity> result = userService.getUserEntity(testUserName);

        assertTrue(result.isEmpty());
    }
}