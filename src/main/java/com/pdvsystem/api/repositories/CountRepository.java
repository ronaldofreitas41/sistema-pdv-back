package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.countrecieve.Count;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountRepository extends JpaRepository<Count, UUID> {
}
