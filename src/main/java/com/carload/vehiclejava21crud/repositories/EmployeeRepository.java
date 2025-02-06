package com.carload.vehiclejava21crud.repositories;

import com.carload.vehiclejava21crud.models.Employee;
import com.carload.vehiclejava21crud.models.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, UUID> {

    List<Employee> findByStatus(EmployeeStatus status);
}
