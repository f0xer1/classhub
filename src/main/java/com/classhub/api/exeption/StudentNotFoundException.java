package com.classhub.api.exeption;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message) {

        super(message);
    }
}
