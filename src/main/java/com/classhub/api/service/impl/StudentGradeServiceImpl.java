package com.classhub.api.service.impl;

import com.classhub.api.exeption.StudentGradeNotFoundException;
import com.classhub.api.model.links.StudentGrade;
import com.classhub.api.repository.StudentGradeRepository;
import com.classhub.api.service.StudentGradeService;
import com.classhub.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGradeServiceImpl implements StudentGradeService {
    private final StudentGradeRepository studentGradeRepository;
    private final StudentService studentService;

    @Override
    public StudentGrade addGrade(StudentGrade studentGrade) {
        return studentGradeRepository.save(studentGrade);
    }

    @Override
    public List<StudentGrade> getGradesForStudent(Long studentId) {
        List<StudentGrade> grades = studentGradeRepository.findAllByStudent(studentService.findById(studentId));
        if (grades.isEmpty()) {
            throw new StudentGradeNotFoundException("No grades found for student with id: " + studentId);
        }
        return grades;
    }


}
