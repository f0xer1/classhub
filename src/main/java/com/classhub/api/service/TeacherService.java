package com.classhub.api.service;

import com.classhub.api.model.users.Teacher;

import java.util.Optional;

public interface TeacherService {
   void createTeacher(Long id) ;

    Optional<Teacher> getTeacherById(Long id);

    Teacher getTeacherByUsername(String username);

   Teacher  editTeacher(Teacher teacher, String username);

}
