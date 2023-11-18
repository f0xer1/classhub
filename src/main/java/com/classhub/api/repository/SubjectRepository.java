package com.classhub.api.repository;

import com.classhub.api.model.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
     boolean existsByName(String name);

}
