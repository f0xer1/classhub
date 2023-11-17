package com.classhub.api.service;

import com.classhub.api.model.Student;
import com.classhub.api.model.dto.StudentDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface StudentService {
    ResponseEntity<String> createStudent(Long id) ;
    Optional<Student> getStudentById(Long id);

    Student getStudentByUsername(String username);

   ResponseEntity<String>  editStudent(StudentDto studentDto);
}
