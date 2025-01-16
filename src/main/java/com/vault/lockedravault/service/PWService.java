package com.vault.lockedravault.service;

import com.vault.lockedravault.model.NewUserDataRequest;
import com.vault.lockedravault.model.UserDataForDomain;
import com.vault.lockedravault.repository.PWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PWService {

    private final PWRepository pwRepository;

    @Autowired
    public PWService(PWRepository pwRepository) {
        this.pwRepository = pwRepository;
    }

    public List<UserDataForDomain> getAllDataForDomain() {
        return pwRepository.findAll();
    }

    public UserDataForDomain saveNewPWData(NewUserDataRequest userData) {
        UserDataForDomain newUserData = new UserDataForDomain(userData.domain(), userData.userNameForDomain(), userData.passwordForDomain());
        return pwRepository.save(newUserData);
    }
}
