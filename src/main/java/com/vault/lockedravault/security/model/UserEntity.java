package com.vault.lockedravault.security.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column()
    private String email;
    
    @Column(nullable = false)
    private String password;

    public UserEntity(String userName, String email, String password) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public UserEntity() {
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
