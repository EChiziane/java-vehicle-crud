package com.carload.vehiclejava21crud.services;

import com.carload.vehiclejava21crud.models.EmployeeEntity;
import com.carload.vehiclejava21crud.models.EmployeeStatus;
import com.carload.vehiclejava21crud.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<EmployeeEntity> getActiveEmployees() {
        return employeeRepository.findByStatus(EmployeeStatus.ATIVO);
    }

    public List<EmployeeEntity> getInactiveEmployees() {
        return employeeRepository.findByStatus(EmployeeStatus.INATIVO);
    }

    public Optional<EmployeeEntity> getEmployeeById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public EmployeeEntity updateEmployee(UUID id, EmployeeEntity updatedEmployee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    updatedEmployee.setId(existingEmployee.getId()); // Garante que o ID permanece o mesmo
                    return employeeRepository.save(updatedEmployee);
                })
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + id));
    }

    @Transactional
    public void deleteEmployee(UUID id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return employeeRepository.existsById(id);
    }
}
