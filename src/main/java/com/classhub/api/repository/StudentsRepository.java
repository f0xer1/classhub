package com.classhub.api.repository;

import com.classhub.api.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students,Long> {
}
