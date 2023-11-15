package com.classhub.api.service;

import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<String> createStudent(Long id) ;
}
