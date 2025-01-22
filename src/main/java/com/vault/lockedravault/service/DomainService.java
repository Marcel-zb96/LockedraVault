package com.vault.lockedravault.service;

import com.vault.lockedravault.model.entity.Domain;
import com.vault.lockedravault.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {

    private final DomainRepository domainRepository;

    @Autowired
    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public List<Domain> getAllDomain() {
        return this.domainRepository.findAll();
    }

    public Domain saveOrGetDomain(String domainName, String domainUrl) {
        try {
            Domain newDomain = new Domain(domainName, domainUrl);
            return domainRepository.save(newDomain);
        } catch (Exception e) {
            // Catch the exception if the entity already saved in the DB
            return domainRepository.findByDomainName(domainName).orElseThrow();
        }
    }
}
