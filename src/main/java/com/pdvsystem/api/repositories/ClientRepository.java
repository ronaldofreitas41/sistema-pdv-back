package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findByCompanyId(String companyId);
}
