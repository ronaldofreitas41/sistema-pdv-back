package com.pdvsystem.api.domain.user;

public enum UserRoles {

    ADMIN("admin"),
    MANAGER("manager"),
    USER("user");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
