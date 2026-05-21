package com.pdvsystem.api.domain.countpay;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "counts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContasPagar {

    @Id
    @GeneratedValue
    private UUID id;

    private String descricao;
    private String categoria;
    private String status;
    private Double valor;
    private Date vencimento;

}
