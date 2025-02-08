package com.carload.vehiclejava21crud.services;

import com.carload.vehiclejava21crud.models.VehicleEntity;
import com.carload.vehiclejava21crud.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<VehicleEntity> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<VehicleEntity> getVehicleById(UUID id) {
        return vehicleRepository.findById(id);
    }

    public VehicleEntity saveVehicle(VehicleEntity vehicleEntity) {
        return vehicleRepository.save(vehicleEntity);
    }

    public VehicleEntity updateVehicle(VehicleEntity vehicleEntity) {
        if (!vehicleRepository.existsById(vehicleEntity.getId())) {
            throw new IllegalArgumentException("Vehicle not found with ID: " + vehicleEntity.getId());
        }
        return vehicleRepository.save(vehicleEntity);
    }

    @Transactional
    public void deleteVehicleById(UUID id) {
        vehicleRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return vehicleRepository.existsById(id);
    }
}
