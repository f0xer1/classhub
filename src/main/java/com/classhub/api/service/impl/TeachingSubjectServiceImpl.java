package com.classhub.api.service.impl;

import com.classhub.api.exeption.TeachingSubjectAlreadyExistsException;
import com.classhub.api.model.dto.TeachingSubjectDto;
import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.repository.SubjectRepository;
import com.classhub.api.repository.TeachingPeriodRepository;
import com.classhub.api.repository.TeachingSubjectRepository;
import com.classhub.api.service.TeachingSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeachingSubjectServiceImpl implements TeachingSubjectService {
    private final TeachingSubjectRepository teachingSubjectRepository;
    private final SubjectRepository subjectRepository;
    private final TeachingPeriodRepository teachingPeriodRepository;
    @Override
    public TeachingSubject addTeachingSubject(TeachingSubjectDto teachingSubjectDto) {
        if (teachingSubjectRepository.existsBySubjectAndTeachingPeriod(subjectRepository.findById(teachingSubjectDto.getSubjectId()),
                teachingPeriodRepository.findById(teachingSubjectDto.getTeachingPeriodId()))){
            throw new TeachingSubjectAlreadyExistsException(
                    "TeachingSubject  is already in use");
        }
        TeachingSubject teachingSubject = new TeachingSubject(
                subjectRepository.findById(teachingSubjectDto.getTeachingPeriodId()),
                teachingPeriodRepository.findById(teachingSubjectDto.getSubjectId()
        ));
        return  teachingSubjectRepository.save(teachingSubject);
    }

    @Override
    public Optional<TeachingSubject> findById(Long id) {
        return teachingSubjectRepository.findById(id);
    }
}
