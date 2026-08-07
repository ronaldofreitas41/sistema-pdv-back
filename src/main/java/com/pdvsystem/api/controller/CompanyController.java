package com.pdvsystem.api.controller;

import com.pdvsystem.api.domain.company.Company;
import com.pdvsystem.api.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String password = body.get("password");
        Company c = companyService.createCompany(name, password);
        return ResponseEntity.ok(c);
    }
}
