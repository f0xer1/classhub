package com.classhub.api.service;

import org.springframework.http.ResponseEntity;

public interface TeacherService {
    ResponseEntity<String> createTeacher(Long id) ;
}
