package com.classhub.api.controller;

import com.classhub.api.model.dto.StudentSubjectDto;
import com.classhub.api.model.links.StudentSubject;
import com.classhub.api.service.StudentSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/student-subject")
@RequiredArgsConstructor
public class StudentSubjectController {
    private final StudentSubjectService studentSubjectService;
    @PostMapping("add")
    public ResponseEntity<String> addStudentSubject(@RequestBody StudentSubjectDto studentSubjectDto){
        return new ResponseEntity<>(studentSubjectService.addStudentSubject(studentSubjectDto), HttpStatus.OK);
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<List<StudentSubject>> getStudentSubjectForStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentSubjectService.getStudentSubjectForStudent(id), HttpStatus.OK);
    }
}
