package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    List<Supplier> findByCompanyId(String companyId);
}
