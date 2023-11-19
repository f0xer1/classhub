package com.classhub.api.service.impl;

import com.classhub.api.service.TeachingSubjectsTeachersService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeachingSubjectsTeachersServiceImpl implements TeachingSubjectsTeachersService {
    private final EntityManager entityManager;
    @Transactional
    public ResponseEntity<String> add(Long teachers_id, Long teaching_subject_id) {
        try {
            String sql = "INSERT INTO teaching_subjects_teachers (teachers_id, teaching_subject_id) VALUES (:teacherId, :subjectId)";
            entityManager.createNativeQuery(sql)
                    .setParameter("teacherId", teachers_id)
                    .setParameter("subjectId", teaching_subject_id)
                    .executeUpdate();

            return new ResponseEntity<>("Association added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add association", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
