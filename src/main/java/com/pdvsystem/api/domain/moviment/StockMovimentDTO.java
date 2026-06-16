package com.pdvsystem.api.domain.moviment;

import java.util.UUID;

public record StockMovimentDTO(String type, Integer quantidade, UUID productId) {
}
