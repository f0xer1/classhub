package com.classhub.api.repository;

import com.classhub.api.model.links.StudentGrade;
import com.classhub.api.model.subjects.Task;
import com.classhub.api.model.users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {
    List<StudentGrade> findAllByStudent(Optional<Student> byId);



    List<StudentGrade> findAllByTask(Task task);
}
