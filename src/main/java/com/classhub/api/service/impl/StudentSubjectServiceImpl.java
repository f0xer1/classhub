package com.classhub.api.service.impl;

import com.classhub.api.exeption.StudentSubjectNotFoundException;
import com.classhub.api.model.links.StudentSubject;
import com.classhub.api.repository.StudentSubjectRepository;
import com.classhub.api.service.StudentService;
import com.classhub.api.service.StudentSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentSubjectServiceImpl implements StudentSubjectService {
    private final StudentSubjectRepository subjectRepository;
    private final StudentService studentService;

    @Override
    public StudentSubject addStudentSubject(StudentSubject studentSubject) {
        return subjectRepository.save(studentSubject);
    }

    @Override
    public List<StudentSubject> getStudentSubjectForStudent(Long studentId) {
        List<StudentSubject> studentSubjects = subjectRepository.findAllByStudent(studentService.findById(studentId));
        if (studentSubjects.isEmpty()) {
            throw new StudentSubjectNotFoundException("No student subjects found for student with id: " + studentId);
        }
        return studentSubjects;
    }


}
