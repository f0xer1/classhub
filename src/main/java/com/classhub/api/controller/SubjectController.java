package com.classhub.api.controller;

import com.classhub.api.model.subjects.Subject;
import com.classhub.api.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/add")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        return new ResponseEntity<>(subjectService.addSubject(subject), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubject() {
        return new ResponseEntity<>(subjectService.getAllSubject(), HttpStatus.OK);
    }

}
