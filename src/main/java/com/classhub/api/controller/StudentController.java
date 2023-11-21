package com.classhub.api.controller;

import com.classhub.api.model.dto.Student.StudentDto;
import com.classhub.api.model.dto.Student.StudentUpdateDto;
import com.classhub.api.model.mapper.StudentMapper;
import com.classhub.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @PutMapping("/{username}")
    public ResponseEntity<StudentDto> editStudent(@PathVariable String username, @RequestBody StudentUpdateDto updateDto) {
        var student = studentService.editStudent(studentMapper.toStudent(updateDto), username);
        return new ResponseEntity<>(studentMapper.toStudentDTO(student), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable Long id) {
        return ResponseEntity.of(studentService.findById(id).map(studentMapper::toStudentDTO));
    }
}
