package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.countpay.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContasPagarRepository extends JpaRepository<ContasPagar, UUID> {
}
