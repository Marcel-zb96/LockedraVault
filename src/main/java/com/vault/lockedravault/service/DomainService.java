package com.vault.lockedravault.service;

import com.vault.lockedravault.model.entity.DomainData;
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

    public List<DomainData> getAllDomain() {
        return this.domainRepository.findAll();
    }

}
