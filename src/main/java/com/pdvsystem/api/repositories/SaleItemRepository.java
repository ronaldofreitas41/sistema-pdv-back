package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.sale.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SaleItemRepository extends JpaRepository<SaleItem, UUID> {
}