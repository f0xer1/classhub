package com.classhub.api.controller;

import com.classhub.api.model.dto.TeacherSubjectDto;
import com.classhub.api.model.dto.TeachingSubjectDto;
import com.classhub.api.model.subjects.Subject;
import com.classhub.api.model.subjects.TeachingPeriod;
import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.service.SubjectService;
import com.classhub.api.service.TeachingPeriodService;
import com.classhub.api.service.TeachingSubjectService;
import com.classhub.api.service.TeachingSubjectsTeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    private final TeachingPeriodService teachingPeriodService;
    private final TeachingSubjectService teachingSubjectService;
    private final TeachingSubjectsTeachersService teachingSubjectsTeachersService;

    @PostMapping("/addSubject")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        return new ResponseEntity<>(subjectService.addSubject(subject), HttpStatus.CREATED);
    }

    @GetMapping("/allSubject")
    public ResponseEntity<List<Subject>> getAllSubject() {
        return new ResponseEntity<>(subjectService.getAllSubject(), HttpStatus.OK);
    }

    @PostMapping("/addTeachingPeriod")
    public ResponseEntity<TeachingPeriod> addTeachingPeriod(@RequestBody TeachingPeriod teachingPeriod) {
        return new ResponseEntity<>(teachingPeriodService.addTeachingPeriod(teachingPeriod), HttpStatus.CREATED);
    }

    @GetMapping("/allTeachingPeriod")
    public ResponseEntity<List<TeachingPeriod>> getAllTeachingPeriod() {
        return new ResponseEntity<>(teachingPeriodService.getAllTeachingPeriod(), HttpStatus.OK);
    }

    @PostMapping("/addTeachingSubjects")
    public ResponseEntity<TeachingSubject> addTeachingSubject(@RequestBody TeachingSubjectDto teachingSubjectDto){
        return new ResponseEntity<>(teachingSubjectService.addTeachingSubject(teachingSubjectDto), HttpStatus.CREATED);
    }
    @GetMapping("/allTeachingSubjects")
    public ResponseEntity<List<TeachingSubject>> allTeachingSubject(){
        return new ResponseEntity<>(teachingSubjectService.getAllTeachingSubject(), HttpStatus.OK);
    }
    @PostMapping("/addTeacherForSubject")
    public ResponseEntity<String> addTeacherToSubject(@RequestBody TeacherSubjectDto teacherSubjectDto){
        return teachingSubjectsTeachersService.add(teacherSubjectDto.getTeacherId(),teacherSubjectDto.getSubjectId());
    }
}
