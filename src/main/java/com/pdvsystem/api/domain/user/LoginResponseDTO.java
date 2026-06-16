package com.pdvsystem.api.domain.user;

public record LoginResponseDTO(String token, String email, String name) {
}
