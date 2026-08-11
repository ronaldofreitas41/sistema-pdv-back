package com.pdvsystem.api.domain.client;

import java.util.Date;

public record ClientRequestDTO(String name, String cpf, String email, String telefone, String endereco, String companyId, Double cashback, boolean status) {
}
