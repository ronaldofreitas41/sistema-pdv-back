package com.pdvsystem.api.domain.moviment;

import java.util.Date;

public record CashMovimentRequestDTO(String type, Double amount, String description, Date dataMovimentacao) {
}
