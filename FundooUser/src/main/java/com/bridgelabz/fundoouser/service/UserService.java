package com.bridgelabz.fundoouser.service;

import com.bridgelabz.fundoouser.dto.LoginDTO;
import com.bridgelabz.fundoouser.dto.UserDTO;
import com.bridgelabz.fundoouser.exception.PasswordException;
import com.bridgelabz.fundoouser.exception.UserAlreadyVerified;
import com.bridgelabz.fundoouser.exception.UserException;
import com.bridgelabz.fundoouser.exception.UserNotVerified;
import com.bridgelabz.fundoouser.model.User;
import com.bridgelabz.fundoouser.repository.UserRepository;
import com.bridgelabz.fundoouser.util.EmailSenderService;
import com.bridgelabz.fundoouser.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    UserRepository repo;

    @Autowired
    EmailSenderService sender;

    @Autowired
    TokenUtil tokenUtil;

    @Override
    public String registerUser(UserDTO dto) {
        Optional<User> matcher = repo.findByEmail(dto.getEmail());
        String token;
        if (matcher.isPresent()) {
            throw new UserException("Email Already Registered");
        } else {
            User user = new User(dto);
            repo.save(user);
            token = tokenUtil.createToken(user.getUserId());
            sender.sendEmail(user.getEmail(), "User Successfully Registered", "Hi, " + user.getFName() +
                    "Welcome To Fundoo Application \n" + "for verification click here: http://localhost:9000/user/verify/" +
                    tokenUtil.createToken(user.getUserId()) + " \n" + "\n click on the following link to retrieve data : \n http://localhost:9000/user/findById/"
                    + token);
        }
        return token;
    }

    //to retrieve list of all users
    @Override
    public List<User> getUser() {
        List<User> list = repo.findAll();
        if (list.isEmpty()) {
            throw new UserException("There are no users added");
        } else {
            return list;
        }
    }

    @Override
    public User getById(String token) {
        Integer userId = tokenUtil.decodeToken(token);
        Optional<User> user = repo.findById(userId);
        if (user.isEmpty()) {
            throw new UserException("There are no users with given id");
        } else {
            sender.sendEmail(user.get().getEmail(), "User successfully retrieved", "for User : \n"
                    + user + "\n click on following link to retrieve data : \n http://localhost:9000/user/findByid/"
                    + token);
        }
        return user.get();
    }

    @Override
    public User loginUser(LoginDTO dto) {
        Optional<User> user = repo.findByEmail(dto.getEmail());
        if (!user.get().isVerified()) {
            throw new UserNotVerified("check your email and verify your account");
        } else {
            if (user.get().equals(null)) {
                throw new UserException("There are no users with given email id");
            } else {
                if (!dto.getPassword().equals(user.get().getPassword())) {
                    throw new PasswordException("Invalid password");
                } else {
                    sender.sendEmail(user.get().getEmail(), "User successfully login", "Hi, " + user.get().getFName()
                            + " Welcome to fundoo application \n"
                            + "to get account information : \n"
                            + " http://localhost:9000/user/findById/" + tokenUtil.createToken(user.get().getUserId()) + " \n"
                            + "\n to update account information : \n http://localhost:9000/user/update/"
                            + tokenUtil.createToken(user.get().getUserId())
                            + "\n to delete account information : \n http://localhost:9000/user/delete/"
                            + tokenUtil.createToken(user.get().getUserId()));
                    return user.get();
                }
            }
        }
    }

    @Override
    public User verifyUser(String token) {
        Integer id = tokenUtil.decodeToken(token);
        Optional<User> user = repo.findById(id);
        if (user.isEmpty()) {
            throw new UserException("There are no users with given id");
        } else if (user.get().isVerified()) {
            throw new UserAlreadyVerified("no need to verify user again");
        } else {
            user.get().setVerified(true);
            repo.save(user.get());
            sender.sendEmail(user.get().getEmail(), "verification", "hi " + user.get().getFName() + " ,Welcome to fundoo application.. /n"
                    + "your account is verified. \n to log in, in application click herer : \n"
                    + "http://localhost:9000/user/login");
            return user.get();
        }
    }

    @Override
    public User updateById(String token, UserDTO dto) {
        Integer userId = tokenUtil.decodeToken(token);
        Optional<User> user = repo.findById(userId);
        User updateUser = null;
        if (user.isPresent()) {
            updateUser = new User(userId, dto);
            repo.save(updateUser);
            sender.sendEmail(user.get().getEmail(), "User successfully updated", "for User : \n"
                    + user + "\n click on following link to retrieve data : \n http://localhost:9000/user/findByid/"
                    + token);
            return updateUser;
        } else {
            throw new UserException("User Not Found");
        }

    }

    //to delete user by id
    public Object deleteById(String token) {
        Integer userId = tokenUtil.decodeToken(token);
        Optional<User> user = repo.findById(userId);
        if (user.isEmpty()) {
            throw new UserException("Invalid token..please input valid token");
        } else {
            sender.sendEmail(((User) user.get()).getEmail(), "User successfully deleted", "User : \n" + user.get());
            repo.deleteById(userId);
            return user.get();
        }
    }

    public String changePassword(UserDTO dto) {
        Optional<User> user = repo.findByEmail(dto.getEmail());
        if (user.equals(null)) {
            throw new UserException("There are no users with given id");
        }
        user.get().setPassword(dto.getPassword());
        repo.save(user.get());
        sender.sendEmail(user.get().getEmail(), "password successfully changed", "someone just logged in,in your account");
        return "New Password Is: " + user.get().getPassword();
    }
}

