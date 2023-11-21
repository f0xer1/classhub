package com.classhub.api.service;

import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.model.users.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeachingSubjectService {
    TeachingSubject addTeachingSubject(TeachingSubject teachingSubject);
    Optional<TeachingSubject> findById(Long id);

    List<TeachingSubject> getAllTeachingSubject();

    List<Teacher> getTeachersBySubjectId(Long subjectId);
}
