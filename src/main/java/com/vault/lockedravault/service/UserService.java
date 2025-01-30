package com.vault.lockedravault.service;

import com.vault.lockedravault.security.model.SignUpRequest;
import com.vault.lockedravault.security.model.UserEntity;
import com.vault.lockedravault.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void createUser(SignUpRequest user) {
        UserEntity userEntity = new UserEntity(user.userName(), user.email(), encoder.encode(user.password()));
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> getUserEntity(String userName) {
        return userRepository.getUserEntityByUserName(userName);
    }
}
