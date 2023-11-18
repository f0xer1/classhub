package com.classhub.api.repository;

import com.classhub.api.model.subjects.TeachingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachingPeriodRepository extends JpaRepository<TeachingPeriod, Long> {
    boolean existsByYearAndSemester(Integer year, Integer semester);
}
