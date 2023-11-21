package com.classhub.api.service.impl;

import com.classhub.api.exeption.TeachingPeriodAlreadyExistsException;
import com.classhub.api.model.subjects.TeachingPeriod;
import com.classhub.api.repository.TeachingPeriodRepository;
import com.classhub.api.service.TeachingPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            return teachingPeriodRepository.findAll();
    }

    @Override
    public Optional<TeachingPeriod> findById(Long id) {
        return teachingPeriodRepository.findById(id);
    }
}
