package com.vault.lockedravault.service;

import com.vault.lockedravault.model.entity.Domain;
import com.vault.lockedravault.repository.DomainRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Domain saveOrGetDomain(String domainName, String domainUrl) {
        return domainRepository.findByDomainName(domainName)
                .orElseGet(() -> domainRepository.save(new Domain(domainName, domainUrl)));
    }
}
