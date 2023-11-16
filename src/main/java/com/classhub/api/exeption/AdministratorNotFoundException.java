package com.classhub.api.exeption;

public class AdministratorNotFoundException extends RuntimeException{
    public AdministratorNotFoundException(String message) {

        super(message);
    }
}
