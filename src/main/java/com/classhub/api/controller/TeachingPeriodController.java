package com.classhub.api.controller;

import com.classhub.api.model.subjects.TeachingPeriod;
import com.classhub.api.service.TeachingPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/teaching-periods")
@RequiredArgsConstructor
public class TeachingPeriodController {
    private final TeachingPeriodService teachingPeriodService;

    @PostMapping("/add")
    public ResponseEntity<TeachingPeriod> addTeachingPeriod(@RequestBody TeachingPeriod teachingPeriod) {
        return new ResponseEntity<>(teachingPeriodService.addTeachingPeriod(teachingPeriod), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeachingPeriod>> getAllTeachingPeriod() {
        return new ResponseEntity<>(teachingPeriodService.getAllTeachingPeriod(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeachingPeriod> findById(@PathVariable Long id){
        return ResponseEntity.of(teachingPeriodService.findById(id));
    }
}
