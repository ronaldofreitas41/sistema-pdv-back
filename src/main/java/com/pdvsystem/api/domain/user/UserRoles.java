package com.pdvsystem.api.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum UserRoles {

    ADMIN("admin"),
    MANAGER("manager"),
    USER("user");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }

    @JsonCreator
    public static UserRoles fromValue(String value) {
        if (value == null) {
            return null;
        }
        return Arrays.stream(values())
                .filter(role -> role.name().equalsIgnoreCase(value) || role.role.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserRoles value: " + value));
    }
}
