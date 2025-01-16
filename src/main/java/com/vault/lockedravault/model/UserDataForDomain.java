package com.vault.lockedravault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UserDataForDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String domainUserName;
    private String domain;
    private String domainPassword;

    public UserDataForDomain(String domain, String domainUserName, String domainPassword) {
        this.domain = domain;
        this.domainUserName = domainUserName;
        this.domainPassword = domainPassword;
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
