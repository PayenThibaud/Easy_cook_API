package org.example.authenticationservice.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException() {
        super("User Already Exist");
    }
}

