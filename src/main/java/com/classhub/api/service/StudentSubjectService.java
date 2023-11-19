package com.classhub.api.service;

import com.classhub.api.model.dto.StudentSubjectDto;
import com.classhub.api.model.links.StudentSubject;

import java.util.List;

public interface StudentSubjectService {
    String addStudentSubject(StudentSubjectDto studentSubjectDto);

    List<StudentSubject> getStudentSubjectForStudent(Long studentId);
}
