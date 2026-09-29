package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.moviment.CashMoviment;
import com.pdvsystem.api.domain.moviment.CashMovimentRequestDTO;
import com.pdvsystem.api.repositories.CashMovimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CashMovimentService {

    @Autowired
    private CashMovimentRepository repository;

    public CashMoviment create(CashMovimentRequestDTO data) {

        CashMoviment moviment = new CashMoviment();

        moviment.setType(data.type());
        moviment.setAmount(data.amount());
        moviment.setDescription(data.description());
        moviment.setDataMovimentacao(data.dataMovimentacao());
        moviment.setCompanyId(data.companyId());

        return repository.save(moviment);
    }

    public List<CashMoviment> getAll() {
        return repository.findAll();
    }

    public CashMoviment getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));
    }

    public void deleteMoviment(UUID id) {
        repository.deleteById(id);
    }
}