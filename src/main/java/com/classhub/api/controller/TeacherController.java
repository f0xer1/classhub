package com.classhub.api.controller;

import com.classhub.api.model.dto.Teacher.TeacherDto;
import com.classhub.api.model.dto.Teacher.TeacherUpdateDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectDto;
import com.classhub.api.model.mapper.TeacherMapper;
import com.classhub.api.model.mapper.TeachingSubjectMapper;
import com.classhub.api.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    private final TeacherMapper teacherMapper;
    private final TeachingSubjectMapper teachingSubjectMapper;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or(hasRole('ROLE_ADMINISTRATOR') and #username == authentication.principal.username)")
    @PutMapping("/{username}")
    public ResponseEntity<TeacherDto> editTeacher(@PathVariable String username, @RequestBody TeacherUpdateDto updateDto) {
        var teacher = teacherService.editTeacher(teacherMapper.toTeacher(updateDto), username);
        return new ResponseEntity<>(teacherMapper.toTeacherDto(teacher), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findTeacherById(@PathVariable Long id) {
        return ResponseEntity.of(teacherService.getTeacherById(id).map(teacherMapper::toTeacherDto));
    }  @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or(hasRole('ROLE_ADMINISTRATOR') and #teacherId == authentication.principal.id)")

    @GetMapping("/{teacherId}/subjects")
    public ResponseEntity<List<TeachingSubjectDto>> getSubjectsByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teachingSubjectMapper.toTeachingSubjectDtoList(teacherService.getSubjectsByTeacherId(teacherId)) );
    }

}
