package com.classhub.api.service;

import com.classhub.api.model.users.Student;

import java.util.Optional;

public interface StudentService {
  void createStudent(Long id) ;
    Optional<Student> getStudentById(Long id);

    Student getStudentByUsername(String username);

   Student  editStudent(Student student, String username);

    Optional<Student> findById(Long studentId);
}
