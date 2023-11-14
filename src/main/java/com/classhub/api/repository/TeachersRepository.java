package com.classhub.api.repository;

import com.classhub.api.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TeachersRepository extends JpaRepository<Teacher, Long> {
    Optional<Object> findByUsername(String username);
}
