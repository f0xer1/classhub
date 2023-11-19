package com.classhub.api.service;

import com.classhub.api.model.users.Teacher;
import com.classhub.api.model.dto.TeacherDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TeacherService {
    ResponseEntity<String> createTeacher(Long id) ;

    Optional<Teacher> getTeacherById(Long id);

    Teacher getTeacherByUsername(String username);

   ResponseEntity<String>  editTeacher(TeacherDto teacherDto);

}
