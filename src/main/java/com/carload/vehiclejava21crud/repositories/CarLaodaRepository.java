package com.carload.vehiclejava21crud.repositories;

import com.carload.vehiclejava21crud.models.CarLoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarLaodaRepository extends JpaRepository<CarLoadEntity, UUID> {
}
