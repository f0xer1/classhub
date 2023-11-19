package com.classhub.api.service.impl;

import com.classhub.api.model.dto.StudentGradeDto;
import com.classhub.api.model.links.StudentGrade;
import com.classhub.api.repository.StudentGradeRepository;
import com.classhub.api.service.StudentGradeService;
import com.classhub.api.service.StudentService;
import com.classhub.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGradeServiceImpl implements StudentGradeService {
    private final StudentGradeRepository studentGradeRepository;
    private final StudentService studentService;
    private final TaskService taskService;
    @Override
    public String addGrade(StudentGradeDto studentGradeDto) {
        studentGradeRepository.save(mapToStudentGrade(studentGradeDto));
        return "ok";
    }

    @Override
    public List<StudentGrade> getGradesForStudent(Long studentId) {
        return studentGradeRepository.findAllByStudent(studentService.findById(studentId));
    }

    public StudentGrade mapToStudentGrade(StudentGradeDto studentGradeDto){
        StudentGrade studentGrade= new StudentGrade();
        studentGrade.setGrade(studentGradeDto.getGrade());
        studentGrade.setStudent(studentService.findById(studentGradeDto.getStudentId()).get());
        studentGrade.setTask(taskService.findById( studentGradeDto.getTaskId()).get());
        return studentGrade;
    }
}
