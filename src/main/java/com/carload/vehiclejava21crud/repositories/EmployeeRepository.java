package com.carload.vehiclejava21crud.repositories;

import com.carload.vehiclejava21crud.models.EmployeeEntity;
import com.carload.vehiclejava21crud.models.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository  extends JpaRepository<EmployeeEntity, UUID> {

    List<EmployeeEntity> findByStatus(EmployeeStatus status);
}
