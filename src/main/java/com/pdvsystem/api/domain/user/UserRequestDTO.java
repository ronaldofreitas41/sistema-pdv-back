package com.pdvsystem.api.domain.user;

public record UserRequestDTO(String id, String name,String email,String password,UserRoles role) {
}
