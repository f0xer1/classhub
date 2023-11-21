package com.classhub.api.controller;

import com.classhub.api.model.dto.StudentGrade.StudentGradeCreationDto;
import com.classhub.api.model.dto.StudentGrade.StudentGradeDto;
import com.classhub.api.model.mapper.StudentGradeMapper;
import com.classhub.api.service.StudentGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/grades")
@RequiredArgsConstructor
public class StudentGradeController {
    private final StudentGradeService studentGradeService;
    private final StudentGradeMapper studentGradeMapper;
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @PostMapping("add")
    public ResponseEntity<StudentGradeDto> addGrades(@RequestBody StudentGradeCreationDto studentGradeCreationDto) {
        var grade = studentGradeService.addGrade(studentGradeMapper.toStudentGrade(studentGradeCreationDto));
        return new ResponseEntity<>(studentGradeMapper.toStudentGradeDto(grade), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER') or #id == authentication.principal.id")
    @GetMapping("/student/{id}")
    public ResponseEntity<List<StudentGradeDto>> getGradesForStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentGradeMapper.toStudentGradeDtoList( studentGradeService.getGradesForStudent(id)), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping("/{id}")
    public ResponseEntity<StudentGradeDto> findById(@PathVariable Long id){
        return ResponseEntity.of(studentGradeService.findById(id).map(studentGradeMapper::toStudentGradeDto));
    }


}
