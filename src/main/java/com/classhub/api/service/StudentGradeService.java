package com.classhub.api.service;

import com.classhub.api.model.links.StudentGrade;

import java.util.List;

public interface StudentGradeService {
    StudentGrade addGrade(StudentGrade studentGrade);

    List<StudentGrade> getGradesForStudent(Long studentId);
}
