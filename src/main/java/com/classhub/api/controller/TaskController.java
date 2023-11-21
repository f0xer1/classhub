package com.classhub.api.controller;


import com.classhub.api.model.dto.Task.TaskCreationDto;
import com.classhub.api.model.dto.Task.TaskDto;
import com.classhub.api.model.mapper.TaskMapper;
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
    private  final TaskMapper taskMapper;

    @PostMapping("/add")
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskCreationDto taskCreationDto) {
        var task = taskService.addTask(taskMapper.toTask(taskCreationDto));
        return new ResponseEntity<>(taskMapper.toTaskDto(task), HttpStatus.CREATED);
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<List<TaskDto>> getForSubject( @PathVariable Long id) {
        return new ResponseEntity<>(taskMapper.toTaskDtoList(taskService.findBySubject(id)) , HttpStatus.OK);
    }
}
