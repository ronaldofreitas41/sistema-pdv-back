package com.pdvsystem.api.domain.sale;

import java.util.UUID;

public record SaleResponseDTO(UUID id, Double total, Integer cashBack, String clientName, String userName) {
}
