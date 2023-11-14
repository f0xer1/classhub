package com.classhub.api.exeption;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String message) {

        super(message);
    }
}
