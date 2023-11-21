package com.classhub.api.exeption;

public class StudentGradeNotFoundException extends RuntimeException{
    public StudentGradeNotFoundException(String message) {
        super(message);
    }
}
