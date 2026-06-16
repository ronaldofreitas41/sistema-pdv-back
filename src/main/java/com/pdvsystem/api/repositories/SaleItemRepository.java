package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.sale.SaleItem;
import com.pdvsystem.api.domain.sale.VendasTotaisDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SaleItemRepository extends JpaRepository<SaleItem, UUID> {
}