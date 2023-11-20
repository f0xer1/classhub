package com.classhub.api.controller;


import com.classhub.api.model.dto.TaskDto;
import com.classhub.api.model.subjects.Task;
import com.classhub.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<List<Task>> getForSubject( @PathVariable Long id) {
        return new ResponseEntity<>(taskService.findBySubject(id), HttpStatus.OK);
    }





}
