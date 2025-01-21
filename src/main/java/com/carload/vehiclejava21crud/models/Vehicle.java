package com.carload.vehiclejava21crud.models;


import jakarta.persistence.*;

import java.io.Serial;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String model;
    private String manufacturer;
    private String manufacturedYear;
    private String licensePlate;
    private Double dailyRentalPrice;
}
