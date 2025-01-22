package com.vault.lockedravault.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String domainName;

    @Column(nullable = false)
    private String url;

    public Domain(String domainName, String url) {
        this.domainName = domainName;
        this.url = url;
    }

    public Domain() {
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
