package com.carload.vehiclejava21crud.models;

public enum EmployeeGender {
    MASCULINE("masculine"), FEMININE("feminine");

    private String gender;
    private EmployeeGender(String gender) {
        this.gender = gender;
    }


}
