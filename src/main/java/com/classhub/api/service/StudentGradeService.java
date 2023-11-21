package com.classhub.api.service;

import com.classhub.api.model.links.StudentGrade;

import java.util.List;
import java.util.Optional;

public interface StudentGradeService {
    StudentGrade addGrade(StudentGrade studentGrade);

    List<StudentGrade> getGradesForStudent(Long studentId);

    Optional<StudentGrade> findById(Long id);
}
