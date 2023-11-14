package com.classhub.api.repository;

import com.classhub.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Student,Long> {
    Optional<Object> findByUsername(String username);
}
