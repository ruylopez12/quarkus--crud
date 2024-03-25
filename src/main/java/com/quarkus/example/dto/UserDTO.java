package com.quarkus.example.dto;

import jakarta.json.bind.annotation.JsonbProperty;

public class UserDTO {

    @JsonbProperty(value = "username")
    private String username;

    @JsonbProperty(value = "password")
    private String password;

    @JsonbProperty(value = "role")
    private String role;

    @JsonbProperty(value = "firstName")
    private String firstName;

    @JsonbProperty(value = "lastName")
    private String lastName;

    @JsonbProperty(value = "age")
    private int age;

    public UserDTO(long id, String firstName, String username) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserDTO() {
    }
}
