package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.client.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
