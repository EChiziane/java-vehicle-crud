package com.carload.vehiclejava21crud.models;

public class EmployeeInputDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private EmployeeStatus status; // Active, Inactive
    private EmployeeGender genero; // Male, Female, Other

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public EmployeeGender getGenero() {
        return genero;
    }

    public void setGenero(EmployeeGender genero) {
        this.genero = genero;
    }
}
