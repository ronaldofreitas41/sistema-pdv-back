package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.moviment.StockMoviment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StockMovimentRepository extends JpaRepository<StockMoviment, UUID> {
    List<StockMoviment> findByProductCompanyId(String companyId);
}