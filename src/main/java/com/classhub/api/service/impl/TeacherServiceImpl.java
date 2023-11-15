package com.classhub.api.service.impl;

import com.classhub.api.model.Administrator;
import com.classhub.api.model.Teacher;
import com.classhub.api.repository.AdministratorsRepository;
import com.classhub.api.repository.TeachersRepository;
import com.classhub.api.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private  final TeachersRepository teachersRepository;
    @Override
    public ResponseEntity<String> createTeacher(Long id) {
        try {
            teachersRepository.save(new Teacher(id));
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
}
