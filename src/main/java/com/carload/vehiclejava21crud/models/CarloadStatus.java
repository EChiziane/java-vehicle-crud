package com.carload.vehiclejava21crud.models;

public enum CarloadStatus {

    CREATED("criado"),
    ENTREGUE("entregue"),
    PROGRESS("progress"),
    TERMINADO("terminado");

    private String status;

    private CarloadStatus(String status) {
        this.status = status;
    }
}
