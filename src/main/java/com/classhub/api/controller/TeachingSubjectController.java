package com.classhub.api.controller;

import com.classhub.api.model.dto.Student.StudentDto;
import com.classhub.api.model.dto.Teacher.TeacherDto;
import com.classhub.api.model.dto.TeacherSubject.TeacherSubjectDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectCreationDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectDto;
import com.classhub.api.model.mapper.StudentMapper;
import com.classhub.api.model.mapper.TeacherMapper;
import com.classhub.api.model.mapper.TeachingSubjectMapper;
import com.classhub.api.service.TeachingSubjectService;
import com.classhub.api.service.TeachingSubjectsTeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/teaching-subjects")
@RequiredArgsConstructor
public class TeachingSubjectController {
    private final TeachingSubjectService teachingSubjectService;
    private final TeachingSubjectsTeachersService teachingSubjectsTeachersService;

    private final TeachingSubjectMapper teachingSubjectMapper;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PostMapping()
    public ResponseEntity<TeachingSubjectDto> addTeachingSubject(@RequestBody TeachingSubjectCreationDto teachingSubjectDto) {
        var teachingSubject = teachingSubjectService.addTeachingSubject(
                teachingSubjectMapper.toTeachingSubject(teachingSubjectDto));

        return new ResponseEntity<>(teachingSubjectMapper.toTeachingSubjectDto(teachingSubject), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping()
    public ResponseEntity<List<TeachingSubjectDto>> allTeachingSubject() {
        return new ResponseEntity<>(teachingSubjectMapper.toTeachingSubjectDtoList(teachingSubjectService.getAllTeachingSubject()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PostMapping("/teacher")
    public ResponseEntity<TeacherSubjectDto> addTeacherToSubject(@RequestBody TeacherSubjectDto teacherSubjectDto) {
        teachingSubjectsTeachersService.add(teacherSubjectDto.getTeacherId(), teacherSubjectDto.getSubjectId());
        return new ResponseEntity<>(teacherSubjectDto, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TeachingSubjectDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(teachingSubjectService.findById(id).map(teachingSubjectMapper::toTeachingSubjectDto));
    }

    @GetMapping("/{subjectId}/teachers")
    public ResponseEntity<List<TeacherDto>> getTeachersBySubjectId(@PathVariable Long subjectId) {

        return ResponseEntity.ok(teacherMapper.toTeacherDtoList(teachingSubjectService.getTeachersBySubjectId(subjectId)));
    }

    @GetMapping("/{subjectId}/students")
    public ResponseEntity<List<StudentDto>> getStudentsBySubjectId(@PathVariable Long subjectId) {
        return ResponseEntity.ok(studentMapper.toStudentDTOList(teachingSubjectService.getStudentBySubjectId(subjectId)));
    }
}
