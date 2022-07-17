package com.bridgelabz.fundoouser.controller;

import com.bridgelabz.fundoouser.dto.LoginDTO;
import com.bridgelabz.fundoouser.dto.ResponseDTO;
import com.bridgelabz.fundoouser.dto.UserDTO;
import com.bridgelabz.fundoouser.model.User;
import com.bridgelabz.fundoouser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService service;

    //To register User
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO dto) {
        String user = service.registerUser(dto);
        ResponseDTO response = new ResponseDTO("User Registered", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //to retrieve all users
    @GetMapping({"/getAll"})
    public ResponseEntity<String> getUsers() {
        List<User> user = this.service.getUser();
        ResponseDTO response = new ResponseDTO("Users :", user);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //to get specific user using token provided
    @GetMapping({"/findById/{token}"})
    public ResponseEntity<ResponseDTO> getById(@PathVariable String token) {
        User user = service.getById(token);
        ResponseDTO response = new ResponseDTO("Requested User", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //to update specific user using token provided
    @PutMapping({"/update/{token}"})
    public ResponseEntity<ResponseDTO> updateById(@PathVariable String token, @Valid @RequestBody UserDTO dto) {
        User user = service.updateById(token, dto);
        ResponseDTO response = new ResponseDTO("User updated : ", user);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //to login in book store app
    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO dto) {
        ResponseDTO response = new ResponseDTO("User Login successefully: ", service.loginUser(dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verifyUser(@PathVariable String token) {
        ResponseDTO response = new ResponseDTO("User verfication :", service.verifyUser(token));
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    //to delete specific user using token provided
    @DeleteMapping({"/delete/{token}"})
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String token) {
        ResponseDTO response = new ResponseDTO("User deleted successfully", service.deleteById(token));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //to reset password of user
    @PutMapping("/forgotPassword")
    public ResponseEntity<ResponseDTO> changePassword(@Valid @RequestBody UserDTO dto) {
        ResponseDTO response = new ResponseDTO("Password Update Successfully :", service.changePassword(dto));
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
}
