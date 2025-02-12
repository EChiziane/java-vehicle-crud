package com.carload.vehiclejava21crud.repositories;

import com.carload.vehiclejava21crud.models.CarLoadEntity;
import com.carload.vehiclejava21crud.models.CarloadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarLoadRepository extends JpaRepository<CarLoadEntity, UUID> {
    List<CarLoadEntity> findByStatus(CarloadStatus status);
}
