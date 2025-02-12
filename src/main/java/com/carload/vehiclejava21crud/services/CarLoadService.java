package com.carload.vehiclejava21crud.services;

import com.carload.vehiclejava21crud.models.CarLoadEntity;

import com.carload.vehiclejava21crud.models.CarloadStatus;
import com.carload.vehiclejava21crud.repositories.CarLoadRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarLoadService {
    private final CarLoadRepository carLoadRepository;

    public CarLoadService(CarLoadRepository carLoadRepository) {
        this.carLoadRepository = carLoadRepository;
    }

    public List<CarLoadEntity> findAllCarLoads() {
        return carLoadRepository.findAll();
    }


    public List<CarLoadEntity> findCarLoadsByStatus(CarloadStatus status) {
        return carLoadRepository.findByStatus(status);
    }

    public Optional<CarLoadEntity> findCarloadById(UUID id) {
        return carLoadRepository.findById(id);
    }

    @Transactional
    public CarLoadEntity saveCarload(CarLoadEntity carLoadEntity) {
        return carLoadRepository.save(carLoadEntity);
    }

    @Transactional
    public void deleteCarload(UUID id) {
        carLoadRepository.deleteById(id);
    }

    public CarLoadEntity updateCarload(CarLoadEntity carLoad) {
        carLoadRepository.save(carLoad);
        return carLoad;
    }



}
