package com.classhub.api.controller.ControllerAdvice;

import com.classhub.api.exeption.*;
import com.classhub.api.model.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ClassHubApiControllerAdvice {

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            UserAlreadyExistsException.class,
            MaxUploadSizeExceededException.class,
            SubjectAlreadyExistsException.class,
            TeachingPeriodAlreadyExistsException.class,
            TeachingSubjectAlreadyExistsException.class,
            TeachingSubjectsException.class
    })
    public ResponseEntity<ExceptionResponse> handleBadRequest(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler({InvalidPasswordException.class, ChangeException.class})
    public ResponseEntity<ExceptionResponse> handleForbidden(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(exceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler({
            UserNotFoundException.class,
            AdministratorNotFoundException.class,
            StudentNotFoundException.class,
            StudentGradeNotFoundException.class,
            TeacherNotFoundException.class,
            RoleNotFoundException.class,
            TeachingSubjectsNotFoundException.class
    })
    public ResponseEntity<ExceptionResponse> handleNotFound(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationError(
            MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors().stream()
                .filter(fe -> fe.getDefaultMessage() != null)
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
        return ResponseEntity.badRequest().body(errors);
    }

    private ExceptionResponse exceptionResponse(String message) {
        return new ExceptionResponse(message,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    }
}