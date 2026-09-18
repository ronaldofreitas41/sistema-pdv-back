package com.pdvsystem.api.domain.count;

import java.util.Date;
import java.util.UUID;

public record CountRequestDTO(
	String descricao,
	String categoria,
	String status,
	Double valor,
	Date vencimento,
	String type,
	String userId,
	String companyId,
	UUID supplierId,
	UUID clientId
) {
}
