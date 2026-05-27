package com.pdvsystem.api.domain.product;

public record ProductRequestDTO(String codigo, Integer estoque, Integer estoqueMin, Double custo, Double preco,
                                String categoria, String nome){

}
