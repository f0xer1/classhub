package com.classhub.api.exeption;

public class StudentSubjectNotFoundException extends RuntimeException{
    public StudentSubjectNotFoundException(String message) {
        super(message);
    }
}
