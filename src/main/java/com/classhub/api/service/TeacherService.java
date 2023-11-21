package com.classhub.api.service;

import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.model.users.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
   void createTeacher(Long id) ;

    Optional<Teacher> getTeacherById(Long id);

    Teacher getTeacherByUsername(String username);

   Teacher  editTeacher(Teacher teacher, String username);

    List<TeachingSubject> getSubjectsByTeacherId(Long teacherId);
}
