package com.classhub.api.service;

import com.classhub.api.model.dto.TeachingSubjectDto;
import com.classhub.api.model.subjects.TeachingSubject;

import java.util.Optional;

public interface TeachingSubjectService {
    TeachingSubject addTeachingSubject(TeachingSubjectDto teachingSubjectDto);
    Optional<TeachingSubject> findById(Long id);
}
