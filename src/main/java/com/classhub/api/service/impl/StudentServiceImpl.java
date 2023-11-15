package com.classhub.api.service.impl;

import com.classhub.api.model.Student;
import com.classhub.api.model.Teacher;
import com.classhub.api.repository.StudentsRepository;
import com.classhub.api.repository.TeachersRepository;
import com.classhub.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private  final StudentsRepository studentsRepository;
    @Override
    public ResponseEntity<String> createStudent(Long id) {
        try {
            studentsRepository.save(new Student(id));
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
}
