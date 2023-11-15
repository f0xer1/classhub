package com.classhub.api.repository;

import com.classhub.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentsRepository extends JpaRepository<Student,Long> {

}
