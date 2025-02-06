package com.carload.vehiclejava21crud.models;

public enum EmployeeStatus {
    ATIVO("ativo"),
    INATIVO("inativo");

    private String status;
    private EmployeeStatus(String status) {
        this.status = status;
    }
}
