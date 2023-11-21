package com.classhub.api.controller;

import com.classhub.api.model.dto.StudentSubject.StudentSubjectCreationDto;
import com.classhub.api.model.dto.StudentSubject.StudentSubjectDto;
import com.classhub.api.model.mapper.StudentSubjectMapper;
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
    private final StudentSubjectMapper studentSubjectMapper;

    @PostMapping("add")
    public ResponseEntity<StudentSubjectDto> addStudentSubject(@RequestBody StudentSubjectCreationDto studentSubjectCreationDto) {
        var studentSubject = studentSubjectService.addStudentSubject(
                studentSubjectMapper.toStudentSubject(studentSubjectCreationDto));
        return new ResponseEntity<>(studentSubjectMapper.toStudentSubjectDto(studentSubject), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<StudentSubjectDto>> getStudentSubjectForStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentSubjectMapper.toStudentSubjectDtoList(
                studentSubjectService.getStudentSubjectForStudent(id)), HttpStatus.OK);
    }
}
