package com.pdvsystem.api.domain.suplier;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Suplier {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String cnpj;
    private String email;
    private String telefone;
    private String endereco;

}
