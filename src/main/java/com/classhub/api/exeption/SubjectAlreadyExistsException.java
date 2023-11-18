package com.classhub.api.exeption;

public class SubjectAlreadyExistsException extends RuntimeException{
    public SubjectAlreadyExistsException(String message) {
        super(message);
    }
}
