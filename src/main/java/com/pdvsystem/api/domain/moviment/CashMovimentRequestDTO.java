package com.pdvsystem.api.domain.moviment;

import java.util.Date;
import java.util.UUID;

public record CashMovimentRequestDTO(String type, Double amount, String description, Date dataMovimentacao, UUID companyId) {
}
