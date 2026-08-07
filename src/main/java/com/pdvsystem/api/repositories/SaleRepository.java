package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    List<Sale> findByUserID(String userID);
}