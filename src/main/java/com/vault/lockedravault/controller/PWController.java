package com.vault.lockedravault.controller;

import com.vault.lockedravault.model.NewUserDataRequest;
import com.vault.lockedravault.model.entity.UserDataForDomain;
import com.vault.lockedravault.security.jwt.JwtUtils;
import com.vault.lockedravault.service.PWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PWController {

    private final PWService pwService;
    private final JwtUtils jwtUtils;

    @Autowired
    public PWController(PWService pwService, JwtUtils jwtUtils) {
        this.pwService = pwService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/data/all")
    public List<UserDataForDomain> getUserDataForDomain(@CookieValue("accessToken") String token) {
        String userName = extractUserNameFromToken(token);
        System.out.println(userName);
        return pwService.getAllDataForDomain(userName);
    }

    @PostMapping("/save")
    public UserDataForDomain saveUserDataForDomain(@CookieValue("accessToken") String token, @RequestBody NewUserDataRequest userData) {
        String userName = extractUserNameFromToken(token);
        return pwService.saveNewPWData(userName, userData);
    }

    private String extractUserNameFromToken(String token) {
        return jwtUtils.getUserNameForJwtToken(token);
    }
}
