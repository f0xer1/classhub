package com.classhub.api.repository;

import com.classhub.api.model.subjects.Subject;
import com.classhub.api.model.subjects.TeachingPeriod;
import com.classhub.api.model.subjects.TeachingSubject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachingSubjectRepository extends JpaRepository<TeachingSubject, Long> {
    boolean existsBySubjectAndTeachingPeriod(Subject subject, TeachingPeriod teachingPeriod);

}
