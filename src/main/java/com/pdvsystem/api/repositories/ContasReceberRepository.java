package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.countrecieve.ContasReceber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContasReceberRepository extends JpaRepository<ContasReceber, UUID> {
}
