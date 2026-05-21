package com.pdvsystem.api.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "Products")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue
    private UUID id;

    private Integer codigo;
    private String nome;
    private String categoria;
    private Double custo;
    private Double preco;
    private Integer estoque;
    private Integer estoqueMin;

}
