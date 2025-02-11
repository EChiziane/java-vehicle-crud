package com.carload.vehiclejava21crud.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name="tb_carload1")
@Getter
@Setter
public class CarLoadEntity implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
    private String ClientName;
    private String ClientNumber;
    private String destino;
    private String valorPago;
    private CarloadStatus status;

}
