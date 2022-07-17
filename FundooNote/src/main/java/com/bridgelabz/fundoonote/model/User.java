package com.bridgelabz.fundoonote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private String fName;
    private String lName;
    private String mobileNumber;
    private String email;
    private String password;
    private String dateOfBirth;
    private LocalDate date = LocalDate.now();
    private boolean verified = false;

}
