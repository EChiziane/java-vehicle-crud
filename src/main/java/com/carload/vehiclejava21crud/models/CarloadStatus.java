package com.carload.vehiclejava21crud.models;

public enum CarloadStatus {

    CRIADO("criado"),
    ENTREGUE("entregue");

    private String status;

    private CarloadStatus(String status) {
        this.status = status;
    }
}
