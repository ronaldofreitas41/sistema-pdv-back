package com.pdvsystem.api.domain.moviment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "cash_moviments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CashMoviment {

    @Id
    @GeneratedValue
    private UUID id;

    private String type;
    private Double amount;
    private String description;


}
