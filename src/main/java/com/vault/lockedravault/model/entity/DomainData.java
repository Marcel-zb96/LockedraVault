package com.vault.lockedravault.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class DomainData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String domainName;

    @Column(nullable = false)
    private String url;

    public DomainData(String domainName, String url) {
        this.domainName = domainName;
        this.url = url;
    }

    public DomainData() {
    }

    public UUID getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDomainName() {
        return domainName;
    }
}
