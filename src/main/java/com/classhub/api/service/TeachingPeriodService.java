package com.classhub.api.service;

import com.classhub.api.model.subjects.TeachingPeriod;

import java.util.List;
import java.util.Optional;

public interface TeachingPeriodService {
    TeachingPeriod addTeachingPeriod(TeachingPeriod teachingPeriod);

   List<TeachingPeriod>  getAllTeachingPeriod();

    Optional<TeachingPeriod> findById(Long id);
}
