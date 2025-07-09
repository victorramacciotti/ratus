package com.academia.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRoles {
    ADMIN("admin"),
    INSTRUCTOR("instructor"),
    RECEPCIONIST("receptionist");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return this.role.replace("ROLE_", "").toLowerCase();
    }

    @JsonCreator
    public static UserRoles fromRole(String role) {
        return switch (role.toLowerCase()) {
            case "admin" -> ADMIN;
            case "instructor" -> INSTRUCTOR;
            case "receptionist" -> RECEPCIONIST;
            default -> throw new IllegalArgumentException("Role inválida: " + role);
        };
    }
}

