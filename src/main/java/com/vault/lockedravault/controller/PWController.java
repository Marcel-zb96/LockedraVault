package com.vault.lockedravault.controller;

import com.vault.lockedravault.model.NewUserDataRequest;
import com.vault.lockedravault.model.entity.UserDataForDomain;
import com.vault.lockedravault.service.PWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PWController {

    private final PWService pwService;

    @Autowired
    public PWController(PWService pwService) {
        this.pwService = pwService;
    }

    @GetMapping("/data/all")
    public List<UserDataForDomain> getUserDataForDomain() {
        return pwService.getAllDataForDomain();
    }

    @PostMapping("/save")
    public UserDataForDomain saveUserDataForDomain(@RequestBody NewUserDataRequest userData, @CookieValue("accessToken") String token) {
        return pwService.saveNewPWData(userData, token);
    }
}
