package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
