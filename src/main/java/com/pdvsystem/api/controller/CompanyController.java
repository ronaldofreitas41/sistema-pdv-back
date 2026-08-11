package com.pdvsystem.api.controller;

import com.pdvsystem.api.domain.company.Company;
import com.pdvsystem.api.domain.company.CompanyRequestDTO;
import com.pdvsystem.api.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyRequestDTO body) {
        Company company = companyService.createCompany(body);
        return ResponseEntity.ok(company);
    }
}
