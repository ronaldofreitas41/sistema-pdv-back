package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.suplier.Suplier;
import com.pdvsystem.api.domain.suplier.SuplierRequestDTO;
import com.pdvsystem.api.repositories.SuplierRepository;

import java.util.List;
import java.util.UUID;

public class SuplierService {

    private SuplierRepository suplierRepository;

    /*
     * Cria Fornecedor
     */

    public Suplier CreateSuplier(SuplierRequestDTO data) {
        Suplier suplier = new Suplier();

        suplier.setCnpj(data.cnpj());
        suplier.setName(data.name());
        suplier.setEmail(data.email());
        suplier.setTelefone(data.telefone());
        suplier.setEndereco(data.endereco());

        return suplierRepository.save(suplier);
    }

    /*
     * Busca todos os Fornecedores
     */

    public List<Suplier> GetAllSupliers() {
        return suplierRepository.findAll();
    }

    /*
     *Busca Fornecedor por ID
     */

    public Suplier GetClientByID(UUID id) {
        return suplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum Cliente encontrado"));
    }

    /*
     * Edita Cliente
     */

    public Suplier EditSuplier(UUID id, SuplierRequestDTO data) {
        Suplier suplier = GetClientByID(id);

        suplier.setName(data.name());
        suplier.setEmail(data.email());
        suplier.setTelefone(data.telefone());
        suplier.setEndereco(data.endereco());
        suplier.setCnpj(data.cnpj());

        return suplierRepository.save(suplier);
    }

    /*
     * Deleta Cliente
     */

    public void deleteClient(UUID id) {
        Suplier suplier = suplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum Cliente encontrado"));
        suplierRepository.delete(suplier);
    }
}
