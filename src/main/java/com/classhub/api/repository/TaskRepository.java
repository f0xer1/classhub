package com.classhub.api.repository;

import com.classhub.api.model.subjects.Task;
import com.classhub.api.model.subjects.TeachingSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByTeachingSubject(TeachingSubject byId);
}
