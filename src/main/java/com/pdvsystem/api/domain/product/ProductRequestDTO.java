package com.pdvsystem.api.domain.product;

public record ProductRequestDTO(String codigo, Integer estoque, Integer estoqueMin, Double custo, Double preco,
                                String categoria, String segmento, String tipoQuantidade, String unidadeMedida, String userId, String companyId, String nome){

}
