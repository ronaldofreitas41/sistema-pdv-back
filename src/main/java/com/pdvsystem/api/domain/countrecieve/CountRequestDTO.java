package com.pdvsystem.api.domain.countrecieve;

import java.util.Date;

public record CountRequestDTO(String descricao, String categoria, String status, Double valor, Date vencimento,String type) {
}
