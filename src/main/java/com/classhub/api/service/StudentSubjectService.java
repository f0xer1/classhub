package com.classhub.api.service;

import com.classhub.api.model.links.StudentSubject;

import java.util.List;

public interface StudentSubjectService {
    StudentSubject addStudentSubject(StudentSubject studentSubject);

    List<StudentSubject> getStudentSubjectForStudent(Long studentId);
}
