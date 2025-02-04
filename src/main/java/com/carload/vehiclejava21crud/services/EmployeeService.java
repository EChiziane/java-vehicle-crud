package com.carload.vehiclejava21crud.services;

import com.carload.vehiclejava21crud.models.Employee;
import com.carload.vehiclejava21crud.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(UUID id) {
            return employeeRepository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(UUID id, Employee employee) {
        return employeeRepository.findById(id).orElse(null);
    }
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }
}
