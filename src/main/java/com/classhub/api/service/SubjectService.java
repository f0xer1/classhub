package com.classhub.api.service;

import com.classhub.api.model.subjects.Subject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubjectService {
    Subject addSubject(Subject subject);

    List<Subject> getAllSubject();
}
