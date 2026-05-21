package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.suplier.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {
}
