package com.carload.vehiclejava21crud.models.dtos;

import com.carload.vehiclejava21crud.models.CarloadStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarLoadOutPutDto {

    private String ClientName;
    private String ClienNumber;
    private String Destino;
    private String valorPago;
    private CarloadStatus status;
}
