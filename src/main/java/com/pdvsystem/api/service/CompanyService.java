package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.company.Company;
import com.pdvsystem.api.domain.company.CompanyRequestDTO;
import com.pdvsystem.api.repositories.CompanyRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company createCompany(CompanyRequestDTO body) {

        Company company = new Company();

        company.setName(body.name());
        company.setPassword(new BCryptPasswordEncoder().encode(body.password()));

        return companyRepository.save(company);
    }

    public Company getById(String id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }
}
