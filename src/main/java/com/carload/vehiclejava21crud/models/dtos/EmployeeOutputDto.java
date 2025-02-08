package com.carload.vehiclejava21crud.models.dtos;


import com.carload.vehiclejava21crud.models.EmployeeEntity;

import java.util.UUID;

public record EmployeeOutputDto(UUID id,
                                String firstName,
                                String lastName,
                                String email,
                                String phone,
                                String status,
                                String genero) {

    public EmployeeOutputDto(EmployeeEntity employee) {
        this(employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getEmail(), employee.getPhone(), employee.getStatus().toString(), employee.getGender().toString());
    }
}
