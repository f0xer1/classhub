package com.classhub.api.service;

import com.classhub.api.model.subjects.TeachingPeriod;

import java.util.List;

public interface TeachingPeriodService {
    TeachingPeriod addTeachingPeriod(TeachingPeriod teachingPeriod);

   List<TeachingPeriod>  getAllTeachingPeriod();
}
