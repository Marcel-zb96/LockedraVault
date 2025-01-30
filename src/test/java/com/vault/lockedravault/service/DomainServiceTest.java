package com.vault.lockedravault.service;

import com.vault.lockedravault.model.entity.Domain;
import com.vault.lockedravault.repository.DomainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DomainServiceTest {

    @Mock
    private DomainRepository domainRepository;
    @InjectMocks
    private DomainService domainService;

    @Test
    void getAllDomain_happyCase() {
        String testDomainName = "testDomain";
        String testDomainUrl = "testDomainUrl";
        List<Domain> testDomains = List.of(new Domain(testDomainName, testDomainUrl));

        when(domainRepository.findAll()).thenReturn(testDomains);

        List<Domain> result = domainService.getAllDomain();

        assertEquals(testDomains, result);
    }

    @Test
    void saveOrGetDomain_domainExists() {
        String testDomainName = "testDomainName";
        String testDomainUrl = "testDomainUrl";

        Domain testDomain = new Domain(testDomainName, testDomainUrl);

        when(domainRepository.findByDomainName(testDomainName)).thenReturn(Optional.of(testDomain));

        Domain result = domainService.saveOrGetDomain(testDomainName, testDomainUrl);

        assertEquals(testDomain, result);

        verify(domainRepository, never()).save(Mockito.any());
    }

    @Test
    void saveOrGetDomain_domainDoesNotExists() {
        String testDomainName = "testDomainName";
        String testDomainUrl = "testDomainUrl";

        Domain testDomain = new Domain(testDomainName, testDomainUrl);

        when(domainRepository.findByDomainName(testDomainName)).thenReturn(Optional.empty());
        when(domainRepository.save(Mockito.any())).thenReturn(testDomain);

        Domain result = domainService.saveOrGetDomain(testDomainName, testDomainUrl);

        assertEquals(testDomain, result);

        verify(domainRepository, times(1)).findByDomainName(testDomainName);
        verify(domainRepository, times(1)).save(Mockito.any());
    }
}