package com.bridgelabz.fundoouser.exception;

public class UserAlreadyVerified extends RuntimeException {
    public UserAlreadyVerified(String message){
        super(message);
    }
}
