package com.carload.vehiclejava21crud.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private Integer manufacturedYear;

    @Column(unique = true, nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Double dailyRentalPrice;
}
