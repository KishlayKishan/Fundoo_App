package com.bridgelabz.fundoouser.model;

import com.bridgelabz.fundoouser.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "UserDetails")
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private String fName;
    private String lName;
    private String mobileNumber;
    private String email;
    private String password;;
    private String dateOfBirth;
    private LocalDate date = LocalDate.now();
    private boolean verified = false;

    public User() {
    }

    public User(UserDTO dto) {
        super();
        this.fName = dto.getFName();
        this.lName = dto.getLName();
        this.mobileNumber = dto.getMobileNumber();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.dateOfBirth = dto.getDateOfBirth();
    }

    public User(Integer userId, UserDTO dto) {
        super();
        this.userId = userId;
        this.fName = dto.getFName();
        this.lName = dto.getLName();
        this.mobileNumber = dto.getMobileNumber();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.dateOfBirth = dto.getDateOfBirth();
    }
}
