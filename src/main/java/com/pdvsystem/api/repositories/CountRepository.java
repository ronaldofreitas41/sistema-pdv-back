package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.count.Count;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CountRepository extends JpaRepository<Count, UUID> {
    List<Count> findByType(String type);
    List<Count> findByTypeAndCompanyId(String type, String companyId);
    List<Count> findByTypeAndUserId(String type, String userId);
    List<Count> findByCompanyId(String companyId);
    List<Count> findByUserId(String userId);
}
