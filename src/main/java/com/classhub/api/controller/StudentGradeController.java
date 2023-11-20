package com.classhub.api.controller;

import com.classhub.api.model.dto.StudentGradeDto;
import com.classhub.api.model.links.StudentGrade;
import com.classhub.api.service.StudentGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/grades")
@RequiredArgsConstructor
public class StudentGradeController {
    private final StudentGradeService studentGradeService;
    @PostMapping("add")
    public ResponseEntity<String> addGrades(@RequestBody StudentGradeDto studentGradeDto) {
        return new ResponseEntity<>(studentGradeService.addGrade(studentGradeDto), HttpStatus.OK);
    }

    @GetMapping("/grades/student/{id}")
    public ResponseEntity<List<StudentGrade>> getGradesForStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentGradeService.getGradesForStudent(id), HttpStatus.OK);
    }


}
