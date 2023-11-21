package com.classhub.api.repository;

import com.classhub.api.model.links.StudentSubject;
import com.classhub.api.model.users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {
    List<StudentSubject> findAllByStudent(Optional<Student> student);
}
