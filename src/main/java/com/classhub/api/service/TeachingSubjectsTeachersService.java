package com.classhub.api.service;

import org.springframework.http.ResponseEntity;

public interface TeachingSubjectsTeachersService {
    ResponseEntity<String> add(Long teacherId, Long subjectId);
}
