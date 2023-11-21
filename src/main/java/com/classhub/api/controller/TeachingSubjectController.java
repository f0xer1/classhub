package com.classhub.api.controller;

import com.classhub.api.model.dto.TeacherSubject.TeacherSubjectDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectCreationDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectDto;
import com.classhub.api.model.mapper.TeachingSubjectMapper;
import com.classhub.api.service.TeachingSubjectService;
import com.classhub.api.service.TeachingSubjectsTeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/teaching-subjects")
@RequiredArgsConstructor
public class TeachingSubjectController {
    private final TeachingSubjectService teachingSubjectService;
    private final TeachingSubjectsTeachersService teachingSubjectsTeachersService;

    private  final TeachingSubjectMapper teachingSubjectMapper;

    @PostMapping("/add")
    public ResponseEntity<TeachingSubjectDto> addTeachingSubject(@RequestBody TeachingSubjectCreationDto teachingSubjectDto) {
        var teachingSubject = teachingSubjectService.addTeachingSubject(
                teachingSubjectMapper.toTeachingSubject(teachingSubjectDto)) ;

        return new ResponseEntity<>(teachingSubjectMapper.toTeachingSubjectDto(teachingSubject), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeachingSubjectDto>> allTeachingSubject() {
        return new ResponseEntity<>(teachingSubjectMapper.toTeachingSubjectDtoList(teachingSubjectService.getAllTeachingSubject()) , HttpStatus.OK);
    }

    @PostMapping("/teacher")
    public ResponseEntity<TeacherSubjectDto> addTeacherToSubject(@RequestBody TeacherSubjectDto teacherSubjectDto) {
        teachingSubjectsTeachersService.add(teacherSubjectDto.getTeacherId(), teacherSubjectDto.getSubjectId());
        return new ResponseEntity<>(teacherSubjectDto, HttpStatus.CREATED);
    }
}
