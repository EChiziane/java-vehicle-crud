package com.carload.vehiclejava21crud.services;

import com.carload.vehiclejava21crud.models.CarLoadEntity;
import com.carload.vehiclejava21crud.repositories.CarLaodaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarLoadService {
    private final CarLaodaRepository carLaodaRepository;
    public CarLoadService(CarLaodaRepository carLaodaRepository) {
        this.carLaodaRepository=carLaodaRepository;
    }

    public List<CarLoadEntity> findAllCarLoads(){
        return carLaodaRepository.findAll();
    }

    public Optional<CarLoadEntity>findCarloadById(UUID id){
        return carLaodaRepository.findById(id);
    }
    @Transactional
    public CarLoadEntity saveCarload(CarLoadEntity carLoadEntity){
        return carLaodaRepository.save(carLoadEntity);
    }
}
