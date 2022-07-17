package com.bridgelabz.fundoouser.service;

import com.bridgelabz.fundoouser.dto.LoginDTO;
import com.bridgelabz.fundoouser.dto.UserDTO;
import com.bridgelabz.fundoouser.model.User;

import java.util.List;

public interface IUserService {

    String registerUser(UserDTO userDTO);

    List<User> getUser();

    User getById(String token);

    User loginUser(LoginDTO dto);

    User verifyUser(String email);

    User updateById(String token, UserDTO dto);

    Object deleteById(String token);

    String changePassword(UserDTO dto);

}
