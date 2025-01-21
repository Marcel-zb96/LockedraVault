package com.vault.lockedravault.service;

import com.vault.lockedravault.model.NewUserDataRequest;
import com.vault.lockedravault.model.entity.DomainData;
import com.vault.lockedravault.model.entity.UserDataForDomain;
import com.vault.lockedravault.repository.PWRepository;
import com.vault.lockedravault.security.jwt.JwtUtils;
import com.vault.lockedravault.security.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PWService {

    private final PWRepository pwRepository;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public PWService(PWRepository pwRepository, UserService userService, JwtUtils jwtUtils) {
        this.pwRepository = pwRepository;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    public List<UserDataForDomain> getAllDataForDomain() {
        return pwRepository.findAll();
    }

    public UserDataForDomain saveNewPWData(NewUserDataRequest userData, String token) {
        DomainData domain = new DomainData(userData.domainName(), userData.domainUrl());
        UserEntity userEntity = getUserEntity(token);

        UserDataForDomain newUserData = new UserDataForDomain(userEntity, domain, userData.userNameForDomain(), userData.passwordForDomain());
        return pwRepository.save(newUserData);
    }

    private UserEntity getUserEntity(String token) {
        String userName = jwtUtils.getUserNameForJwtToken(token);
        return userService.getUser(userName);
    }
}
