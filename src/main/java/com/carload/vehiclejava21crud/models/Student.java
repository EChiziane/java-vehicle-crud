package com.carload.vehiclejava21crud.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Student {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String birthday;
}
