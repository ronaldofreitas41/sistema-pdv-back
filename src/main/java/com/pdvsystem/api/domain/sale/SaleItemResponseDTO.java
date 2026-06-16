package com.pdvsystem.api.domain.sale;

import java.util.UUID;

public record SaleItemResponseDTO(UUID id, UUID productId, String productName, Integer quantity, Double unitPrice) {
}
