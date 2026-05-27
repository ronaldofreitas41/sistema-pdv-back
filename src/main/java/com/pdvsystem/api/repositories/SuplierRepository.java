package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.suplier.Suplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SuplierRepository extends JpaRepository<Suplier, UUID> {
}
