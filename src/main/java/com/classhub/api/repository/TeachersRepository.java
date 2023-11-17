package com.classhub.api.repository;

import com.classhub.api.model.users.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends JpaRepository<Teacher, Long> {



}
