package org.fawry.bankapisystem.dto.auth;


import jakarta.validation.constraints.*;
import org.fawry.bankapisystem.model.enumTypes.RoleType;
import org.hibernate.validator.constraints.UniqueElements;

public class RegisterRequest {

    @NotEmpty(message = "First name shouldn't be empty.")
    @Size(min = 2, message = "size must be at-least 2.")
    private String firstName;
    private String lastName;
    @NotEmpty(message = "Email shouldn't be empty.")
    @Email(message = "Email should be valid.")
    private String email;
    @NotEmpty(message = "Password shouldn't be empty.")
    @Min(value = 8, message = "Password should be 8 characters or more.")
    private String password;
    @NotEmpty(message = "Phone number shouldn't be empty.")
    @Size(min = 11,max = 11, message = "The number should be 11 numbers.")
    @Pattern(regexp = "^[0-9]+$",message = "Only numbers allowed.")
    private String phoneNumber;

    @NotEmpty(message = "Address shouldn't be empty.")
    private String address;

    public RegisterRequest(String firstName, String lastName, String email, String password, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
