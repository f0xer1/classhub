package com.classhub.api.controller;

import com.classhub.api.model.dto.Student.StudentDto;
import com.classhub.api.model.dto.Student.StudentUpdateDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectDto;
import com.classhub.api.model.mapper.StudentMapper;
import com.classhub.api.model.mapper.TeachingSubjectMapper;
import com.classhub.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final TeachingSubjectMapper teachingSubjectMapper;
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') ")

    @PutMapping("/{username}")
    public ResponseEntity<StudentDto> editStudent(@PathVariable String username, @RequestBody StudentUpdateDto updateDto) {
        var student = studentService.editStudent(studentMapper.toStudent(updateDto), username);
        return new ResponseEntity<>(studentMapper.toStudentDTO(student), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable Long id) {
        return ResponseEntity.of(studentService.findById(id).map(studentMapper::toStudentDTO));
    }@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER') or #studentId == authentication.principal.id ")
    @GetMapping("/{studentId}/subjects")
    public ResponseEntity<List<TeachingSubjectDto>> getSubjectsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(teachingSubjectMapper.toTeachingSubjectDtoList(studentService.getSubjectsByStudentId(studentId)) );
    }

}
