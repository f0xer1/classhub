package com.classhub.api.service;

import com.classhub.api.model.subjects.TeachingSubject;

import java.util.List;
import java.util.Optional;

public interface TeachingSubjectService {
    TeachingSubject addTeachingSubject(TeachingSubject teachingSubject);
    Optional<TeachingSubject> findById(Long id);

    List<TeachingSubject> getAllTeachingSubject();
}
