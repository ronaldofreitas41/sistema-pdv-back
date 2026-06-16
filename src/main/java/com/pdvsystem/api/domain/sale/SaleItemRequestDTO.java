package com.pdvsystem.api.domain.sale;

import java.util.UUID;

public record SaleItemRequestDTO(
        UUID productId,
        Integer quantity,
        Double unitPrice
) {
}
