package com.classhub.api.service;

import com.classhub.api.model.links.StudentSubject;

import java.util.List;
import java.util.Optional;

public interface StudentSubjectService {
    StudentSubject addStudentSubject(StudentSubject studentSubject);

    List<StudentSubject> getStudentSubjectForStudent(Long studentId);

    Optional<StudentSubject> findById(Long id);

    List<StudentSubject> getStudentSubjectForSubject(Long id);
}
