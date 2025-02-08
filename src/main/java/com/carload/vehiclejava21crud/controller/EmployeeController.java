package com.carload.vehiclejava21crud.controller;

import com.carload.vehiclejava21crud.models.EmployeeEntity;
import com.carload.vehiclejava21crud.models.dtos.EmployeeInputDto;
import com.carload.vehiclejava21crud.models.dtos.EmployeeOutputDto;
import com.carload.vehiclejava21crud.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeOutputDto> getAllEmployees() {
        return convertToDtoList(employeeService.getAllEmployees());
    }

    @GetMapping("/active")
    public List<EmployeeOutputDto> getActiveEmployees() {
        return convertToDtoList(employeeService.getActiveEmployees());
    }

    @GetMapping("/nonactive")
    public List<EmployeeOutputDto> getNonActiveEmployees() {
        return convertToDtoList(employeeService.getInactiveEmployees());
    }

    @PostMapping
    public EmployeeOutputDto addEmployee(@RequestBody EmployeeInputDto employeeRequest) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setStatus(employeeRequest.getStatus());
        employee.setGender(employeeRequest.getGenero());
        employee.setPhone(employeeRequest.getPhone());

        EmployeeEntity savedEmployee = employeeService.addEmployee(employee);
        return new EmployeeOutputDto(savedEmployee);
    }

    // Método auxiliar para converter listas
    private List<EmployeeOutputDto> convertToDtoList(List<EmployeeEntity> employees) {
        return employees.stream().map(EmployeeOutputDto::new).collect(Collectors.toList());
    }
}
