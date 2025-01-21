package com.carload.vehiclejava21crud.services;


import com.carload.vehiclejava21crud.models.Vehicle;
import com.carload.vehiclejava21crud.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle SaveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);

    }

    public Optional<Vehicle> getVehicleById(UUID id) {
        return vehicleRepository.findById(id);
    }

    public void deleteVehicleById(UUID id) {
        vehicleRepository.deleteById(id);

    }

    public void deleteAllVehicles() {
        vehicleRepository.deleteAll();
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}

