package com.carload.vehiclejava21crud.models.dtos;

import com.carload.vehiclejava21crud.models.CarloadStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CarLoadOutPutDto {
private UUID id;
    private String ClientName;
    private String ClientNumber;
    private String Destino;
    private String valorPago;
    private CarloadStatus status;
}
