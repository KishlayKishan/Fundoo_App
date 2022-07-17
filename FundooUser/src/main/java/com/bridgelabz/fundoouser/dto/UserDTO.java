package com.bridgelabz.fundoouser.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "First letter of first name must be capital")
    private String fName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "First letter of last name must be capital")
    private String lName;
    @Pattern(regexp = "[0-9]{10}", message = "Invalid Mobile Number")
    private String mobileNumber;
    @Email(message = "Insert valid email")
    private String email;
    @NotNull(message = "Enter Your Password")
    private String password;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "date of vbirth must be in YYYY-MM-DD format")
    private String dateOfBirth;

    public UserDTO() {
    }
}
