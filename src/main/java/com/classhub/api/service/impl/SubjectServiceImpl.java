package com.classhub.api.service.impl;

import com.classhub.api.exeption.SubjectAlreadyExistsException;
import com.classhub.api.model.subjects.Subject;
import com.classhub.api.repository.SubjectRepository;
import com.classhub.api.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final  SubjectRepository subjectRepository;

    @Override
    public Subject addSubject(Subject subject) {
        if (subjectRepository.existsByName(subject.getName())) {
            throw new SubjectAlreadyExistsException(
                    String.format("Subject %s is already in use", subject.getName()));
        }
        return  subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubject() {
        try {
            return subjectRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
