package com.classhub.api.exeption;

public class TeachingPeriodAlreadyExistsException extends RuntimeException{
    public TeachingPeriodAlreadyExistsException(String message) {
        super(message);
    }
}
