package com.example.sasc_api.domain.Enum;

public enum AcessLevels {

    USER("user"),
    ADMIN("admin");

    private final String role;

    AcessLevels(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
