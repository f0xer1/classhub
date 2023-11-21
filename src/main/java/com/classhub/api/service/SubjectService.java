package com.classhub.api.service;

import com.classhub.api.model.subjects.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Subject addSubject(Subject subject);

    List<Subject> getAllSubject();

    Optional<Subject> findById(Long id);
}
