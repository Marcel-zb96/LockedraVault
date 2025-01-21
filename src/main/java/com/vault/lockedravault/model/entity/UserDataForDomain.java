package com.vault.lockedravault.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vault.lockedravault.security.model.UserEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class UserDataForDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String domainUserName;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "domain_id")
    private DomainData domain;

    @Column(nullable = false)
    private String domainPassword;

    public UserDataForDomain(UserEntity userEntity, DomainData domain, String domainUserName, String domainPassword) {
        this.userEntity = userEntity;
        this.domain = domain;
        this.domainUserName = domainUserName;
        this.domainPassword = domainPassword;
    }

    public UserDataForDomain() {

    }

    public UUID getId() {
        return this.id;
    }

    public DomainData getDomain() {
        return this.domain;
    }

    public String getDomainPassword() {
        return this.domainPassword;
    }

    public String getDomainUserName() {
        return this.domainUserName;
    }
}
