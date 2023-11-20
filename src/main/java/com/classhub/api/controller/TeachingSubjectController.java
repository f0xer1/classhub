package com.classhub.api.controller;

import com.classhub.api.model.dto.TeacherSubjectDto;
import com.classhub.api.model.dto.TeachingSubjectDto;
import com.classhub.api.model.subjects.TeachingSubject;
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

    @PostMapping("/add")
    public ResponseEntity<TeachingSubject> addTeachingSubject(@RequestBody TeachingSubjectDto teachingSubjectDto) {
        return new ResponseEntity<>(teachingSubjectService.addTeachingSubject(teachingSubjectDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeachingSubject>> allTeachingSubject() {
        return new ResponseEntity<>(teachingSubjectService.getAllTeachingSubject(), HttpStatus.OK);
    }

    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacherToSubject(@RequestBody TeacherSubjectDto teacherSubjectDto) {
        return teachingSubjectsTeachersService.add(teacherSubjectDto.getTeacherId(), teacherSubjectDto.getSubjectId());
    }
}
