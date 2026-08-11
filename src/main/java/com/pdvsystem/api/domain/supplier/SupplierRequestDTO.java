package com.pdvsystem.api.domain.supplier;

public record SupplierRequestDTO(String name, String cnpj, String email, String telefone, String endereco, String companyId) {
}
