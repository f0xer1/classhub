package com.classhub.api.repository;

import com.classhub.api.model.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeachersRepository extends JpaRepository<Teachers, Long> {
}
