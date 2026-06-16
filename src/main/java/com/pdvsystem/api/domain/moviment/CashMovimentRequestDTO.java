package com.pdvsystem.api.domain.moviment;

public record CashMovimentRequestDTO(String type, Double amount, String description) {
}
