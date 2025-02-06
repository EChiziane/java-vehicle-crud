package com.carload.vehiclejava21crud.controller;


import com.carload.vehiclejava21crud.models.Employee;
import com.carload.vehiclejava21crud.models.EmployeeInputDto;
import com.carload.vehiclejava21crud.models.EmployeeOutputDto;
import com.carload.vehiclejava21crud.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<EmployeeOutputDto> employees = employeeService.getAllEmployees().stream().map(EmployeeOutputDto::new).toList();
        return employees;
    }


    @GetMapping("/active")
    public List<EmployeeOutputDto> getActiveEmployees() {
        List<EmployeeOutputDto>employees = employeeService.getActiveEmployees().stream().map(EmployeeOutputDto::new).toList();
        return employees;
    }

    @GetMapping("/nonactive")
    public List<EmployeeOutputDto> getNonActiveEmployees() {
        List<EmployeeOutputDto>employees = employeeService.getNonActiveEmployees().stream().map(EmployeeOutputDto::new).toList();
        return employees;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody EmployeeInputDto employeeRequest) {
        Employee employee = new Employee();
        employee.setLastName(employeeRequest.getFirstName());
        employee.setFirstName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setStatus(employeeRequest.getStatus());
        employee.setGender(employeeRequest.getGenero());
        employee.setPhone(employeeRequest.getPhone());
        return employeeService.addEmployee(employee);
    }
}
