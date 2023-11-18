package com.classhub.api.exeption;

public class TeachingSubjectAlreadyExistsException extends RuntimeException{
    public TeachingSubjectAlreadyExistsException(String message) {
        super(message);
    }
}
