package com.academia.entity.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRoles {
    ADMINN("admin"),
    USER("user");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return this.role;
    }

    @JsonCreator
    public static UserRoles fromRole(String role) {
        return switch (role.toLowerCase()) {
            case "admin" -> ADMINN;
            case "user" -> USER;
            default -> throw new IllegalArgumentException("Role inválida: " + role);
        };
    }
}

