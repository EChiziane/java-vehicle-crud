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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturedYear() {
        return manufacturedYear;
    }

    public void setManufacturedYear(String manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public void setDailyRentalPrice(Double dailyRentalPrice) {
        this.dailyRentalPrice = dailyRentalPrice;
    }
}
