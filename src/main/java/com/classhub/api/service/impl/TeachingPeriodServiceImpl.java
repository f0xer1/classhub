package com.classhub.api.service.impl;

import com.classhub.api.exeption.SubjectAlreadyExistsException;
import com.classhub.api.exeption.TeachingPeriodAlreadyExistsException;
import com.classhub.api.model.subjects.Subject;
import com.classhub.api.model.subjects.TeachingPeriod;
import com.classhub.api.repository.SubjectRepository;
import com.classhub.api.repository.TeachersRepository;

import com.classhub.api.repository.TeachingPeriodRepository;
import com.classhub.api.service.TeachingPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachingPeriodServiceImpl implements TeachingPeriodService {
    private final TeachingPeriodRepository teachingPeriodRepository;

    @Override
    public TeachingPeriod addTeachingPeriod(TeachingPeriod teachingPeriod) {
        if (teachingPeriodRepository.existsByYearAndSemester(teachingPeriod.getYear(), teachingPeriod.getSemester())) {
            throw new TeachingPeriodAlreadyExistsException("TeachingPeriod is already in use");
        }
        return teachingPeriodRepository.save(teachingPeriod);
    }

    @Override
    public List<TeachingPeriod> getAllTeachingPeriod() {
        try {
            return teachingPeriodRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
