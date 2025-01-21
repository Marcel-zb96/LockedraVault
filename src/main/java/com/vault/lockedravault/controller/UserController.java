package com.vault.lockedravault.controller;

import com.vault.lockedravault.security.model.JwtResponse;
import com.vault.lockedravault.security.model.LoginRequest;
import com.vault.lockedravault.security.model.SignUpRequest;
import com.vault.lockedravault.security.jwt.JwtUtils;
import com.vault.lockedravault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestBody SignUpRequest user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public JwtResponse signInUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.userName(), loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();

        return new JwtResponse(jwt, userDetails.getUsername());
    }
}
