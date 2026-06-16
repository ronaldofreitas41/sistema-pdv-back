package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.supplier.Supplier;
import com.pdvsystem.api.domain.supplier.SupplierRequestDTO;
import com.pdvsystem.api.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    /*
     * Cria Fornecedor
     */

    public Supplier createSuplier(SupplierRequestDTO data) {
        Supplier supplier = new Supplier();

        supplier.setCnpj(data.cnpj());
        supplier.setName(data.name());
        supplier.setEmail(data.email());
        supplier.setTelefone(data.telefone());
        supplier.setEndereco(data.endereco());

        return supplierRepository.save(supplier);
    }

    /*
     * Busca todos os Fornecedores
     */

    public List<Supplier> getAllSupliers() {
        return supplierRepository.findAll();
    }

    /*
     *Busca Fornecedor por ID
     */

    public Supplier getSuplierByID(UUID id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum Fornecedor encontrado"));
    }

    /*
     * Edita Fornecedor
     */
    public Supplier editSuplier(UUID id, SupplierRequestDTO data) {
        Supplier supplier = getSuplierByID(id);

        supplier.setName(data.name());
        supplier.setEmail(data.email());
        supplier.setTelefone(data.telefone());
        supplier.setEndereco(data.endereco());
        supplier.setCnpj(data.cnpj());

        return supplierRepository.save(supplier);
    }

    /*
     * Deleta Fornecedor
     */
    public void deleteSuplier(UUID id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum Fornecedor encontrado"));
        supplierRepository.delete(supplier);
    }
}
