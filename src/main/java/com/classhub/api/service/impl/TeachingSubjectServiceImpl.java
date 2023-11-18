package com.classhub.api.service.impl;

import com.classhub.api.exeption.TeachingSubjectAlreadyExistsException;
import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.repository.TeachingSubjectRepository;
import com.classhub.api.service.TeachingSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeachingSubjectServiceImpl implements TeachingSubjectService {
    TeachingSubjectRepository teachingSubjectRepository;
    @Override
    public TeachingSubject addTeachingSubject(TeachingSubject teachingSubject) {
        if (teachingSubjectRepository.existsBySubjectAndTeachingPeriod(teachingSubject.getSubject(), teachingSubject.getTeaching_period())) {
            throw new TeachingSubjectAlreadyExistsException(
                    "TeachingSubject  is already in use");
        }
        return  teachingSubjectRepository.save(teachingSubject);
    }
}
