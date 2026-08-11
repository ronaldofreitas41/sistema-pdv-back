package com.pdvsystem.api.repositories;

import com.pdvsystem.api.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByUserId(String userId);
    List<Product> findByCompanyId(String companyId);
    List<Product> findByCompanyIdAndSegmento(String companyId, String segmento);
    List<Product> findByUserIdAndCompanyId(String userId, String companyId);
    List<Product> findByUserIdAndSegmentoAndCompanyId(String userId, String segmento, String companyId);
    List<Product> findBySegmento(String segmento);
    List<Product> findByUserIdAndSegmento(String userId, String segmento);
}
