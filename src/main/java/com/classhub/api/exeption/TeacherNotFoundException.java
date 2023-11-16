package com.classhub.api.exeption;

public class TeacherNotFoundException extends  RuntimeException{
    public TeacherNotFoundException(String message) {

        super(message);
    }
}
