package com.classhub.api.service;

import com.classhub.api.model.dto.StudentGradeDto;
import com.classhub.api.model.links.StudentGrade;

import java.util.List;

public interface StudentGradeService {
    String addGrade(StudentGradeDto studentGradeDto);

    List<StudentGrade> getGradesForStudent(Long studentId);
}
