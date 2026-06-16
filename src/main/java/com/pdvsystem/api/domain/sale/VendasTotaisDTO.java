package com.pdvsystem.api.domain.sale;

import java.util.UUID;

public record VendasTotaisDTO(Integer quantidade, Double valorTotal, Double lucro, String nome, UUID idProduto) {
}
