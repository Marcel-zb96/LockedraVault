package com.vault.lockedravault.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class UserDataForDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String domainUserName;

    @Column(nullable = false)
    private String domain;

    @Column(nullable = false)
    private String domainPassword;

    @Column(nullable = false)
    private String category;

    public UserDataForDomain(String userName, String domain, String domainUserName, String domainPassword, String category) {
        this.userName = userName;
        this.domain = domain;
        this.domainUserName = domainUserName;
        this.domainPassword = domainPassword;
        this.category = category;
    }

    public UserDataForDomain() {

    }

    public UUID getId() {
        return this.id;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getDomainPassword() {
        return this.domainPassword;
    }

    public String getDomainUserName() {
        return this.domainUserName;
    }
}
