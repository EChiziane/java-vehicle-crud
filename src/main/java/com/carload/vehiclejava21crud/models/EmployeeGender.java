package com.carload.vehiclejava21crud.models;

public enum EmployeeGender {
    MASCULINO("masculine"), FEMENINO("feminine");

    private String gender;
    private EmployeeGender(String gender) {
        this.gender = gender;
    }


}
