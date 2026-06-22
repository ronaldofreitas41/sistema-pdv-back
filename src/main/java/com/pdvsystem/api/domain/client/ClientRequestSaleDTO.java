package com.pdvsystem.api.domain.client;

import java.util.Date;

public record ClientRequestSaleDTO(String name, String cpf, String email, String telefone, String endereco, Double cashback, boolean status, Date ultimaCompra, Date validadeCashback) {
}
