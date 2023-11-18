package com.classhub.api.service;

import com.classhub.api.model.dto.TeachingSubjectDto;
import com.classhub.api.model.subjects.TeachingSubject;

public interface TeachingSubjectService {
    TeachingSubject addTeachingSubject(TeachingSubjectDto teachingSubjectDto);
}
