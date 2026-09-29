package com.pdvsystem.api.domain.sale;

import java.util.List;
import java.util.UUID;

public record SaleRequestDTO(
        UUID clientId,
        String userID,
        Double cashBack,
        Double total,
        String pagamento,
        List<SaleItemRequestDTO> items,
        String formaPagamento,
        UUID companyId
) {
}