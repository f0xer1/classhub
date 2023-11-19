package com.classhub.api.controller;

import com.classhub.api.model.dto.StudentGradeDto;
import com.classhub.api.model.dto.StudentSubjectDto;
import com.classhub.api.model.dto.TaskDto;
import com.classhub.api.model.links.StudentGrade;
import com.classhub.api.model.links.StudentSubject;
import com.classhub.api.model.subjects.Task;
import com.classhub.api.service.StudentGradeService;
import com.classhub.api.service.StudentSubjectService;
import com.classhub.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final StudentGradeService studentGradeService;
    private final StudentSubjectService studentSubjectService;

    @PostMapping("add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.CREATED);
    }

    @GetMapping("getForSubject/{id}")
    public ResponseEntity<List<Task>> getForSubject( @PathVariable Long id) {
        return new ResponseEntity<>(taskService.findBySubject(id), HttpStatus.OK);
    }



    @PostMapping("addGrades")
    public ResponseEntity<String> addGrades(@RequestBody StudentGradeDto studentGradeDto) {
        return new ResponseEntity<>(studentGradeService.addGrade(studentGradeDto), HttpStatus.OK);
    }

    @GetMapping("getGradesForStudent/{id}")
    public ResponseEntity<List<StudentGrade>> getGradesForStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentGradeService.getGradesForStudent(id), HttpStatus.OK);
    }


    @PostMapping("addStudentSubject")
    public  ResponseEntity<String> addStudentSubject(@RequestBody StudentSubjectDto studentSubjectDto){
        return new ResponseEntity<>(studentSubjectService.addStudentSubject(studentSubjectDto), HttpStatus.OK);
    }
    @GetMapping("getStudentSubjectForStudent/{id}")
    public ResponseEntity<List<StudentSubject>> getStudentSubjectForStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentSubjectService.getStudentSubjectForStudent(id), HttpStatus.OK);
    }
}
