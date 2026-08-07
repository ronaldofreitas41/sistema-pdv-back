package com.pdvsystem.api.domain.user;

public record RegisterDTO(String email, String password, String name, UserRoles role, String companyId, String companyPassword) {
}
