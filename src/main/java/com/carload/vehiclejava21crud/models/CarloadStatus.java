package com.carload.vehiclejava21crud.models;

public enum CarloadStatus {

    CRIADA,               // A carrada foi registrada no sistema
    EM_TRANSPORTE,        // A carrada está em deslocamento
    ENTREGUE,             // A carrada foi entregue com sucesso
    CANCELADA,            // A carrada foi cancelada antes da entrega
    DEVOLVIDA;            // A carrada foi devolvida ao remetente


    private String status;


}
