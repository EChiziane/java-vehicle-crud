package com.carload.vehiclejava21crud.models;

import java.util.UUID;

public record EmployeeOutputDto(UUID id,
                                String firstName,
                                String lastName,
                                String email,
                                String phone,
                                String status,
                                String gender) {

    public EmployeeOutputDto(Employee employee) {
        this(employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getEmail(), employee.getPhone(), employee.getStatus().toString(), employee.getGender().toString());
    }
}
