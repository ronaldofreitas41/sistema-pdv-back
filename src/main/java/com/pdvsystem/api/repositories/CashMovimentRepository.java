package com.pdvsystem.api.repositories;


import com.pdvsystem.api.domain.moviment.CashMoviment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CashMovimentRepository extends JpaRepository<CashMoviment, UUID> {
}