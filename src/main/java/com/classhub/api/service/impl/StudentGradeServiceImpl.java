package com.classhub.api.service.impl;

import com.classhub.api.exeption.StudentGradeNotFoundException;
import com.classhub.api.model.links.StudentGrade;
import com.classhub.api.model.subjects.Task;
import com.classhub.api.repository.StudentGradeRepository;
import com.classhub.api.service.StudentGradeService;
import com.classhub.api.service.StudentService;
import com.classhub.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentGradeServiceImpl implements StudentGradeService {
    private final StudentGradeRepository studentGradeRepository;
    private final StudentService studentService;
    private final TaskService taskService;

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

    @Override
    public Optional<StudentGrade> findById(Long id) {
        return studentGradeRepository.findById(id);
    }

    @Override
    public List<StudentGrade> findAllByTaskId(Long id) {
        Optional<Task> taskOptional = taskService.findById(id);

        if (taskOptional.isPresent()) {
            return studentGradeRepository.findAllByTask(taskOptional.get());
        } else {
            return List.of();
        }
    }

}
