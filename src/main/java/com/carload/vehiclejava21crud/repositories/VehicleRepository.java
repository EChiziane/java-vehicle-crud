package com.carload.vehiclejava21crud.repositories;

import com.carload.vehiclejava21crud.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

}
