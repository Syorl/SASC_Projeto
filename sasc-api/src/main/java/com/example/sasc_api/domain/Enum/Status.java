package com.example.sasc_api.domain.Enum;

public enum Status {

    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
