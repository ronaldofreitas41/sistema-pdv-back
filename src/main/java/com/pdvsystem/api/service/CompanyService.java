package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.company.Company;
import com.pdvsystem.api.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company createCompany(String name, String rawPassword) {
        Company c = new Company();
        c.setName(name);
        String hashed = new BCryptPasswordEncoder().encode(rawPassword);
        c.setPassword(hashed);
        return companyRepository.save(c);
    }

    public Company getById(String id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }
}
