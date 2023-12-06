package com.classhub.api.controller;


import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.dto.StudentGrade.StudentGradeDto;
import com.classhub.api.model.dto.Task.TaskCreationDto;
import com.classhub.api.model.dto.Task.TaskDto;
import com.classhub.api.model.mapper.StudentGradeMapper;
import com.classhub.api.model.mapper.TaskMapper;
import com.classhub.api.service.StudentGradeService;
import com.classhub.api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final StudentGradeService studentGradeService;
    private final TaskMapper taskMapper;
    private final StudentGradeMapper studentGradeMapper;

    @Operation(summary = "Create new task", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @PostMapping()
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskCreationDto taskCreationDto) {
        var task = taskService.addTask(taskMapper.toTask(taskCreationDto));
        return new ResponseEntity<>(taskMapper.toTaskDto(task), HttpStatus.CREATED);
    }

    @Operation(summary = "Get task by subject id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TaskDto.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/subjects/{id}")
    public ResponseEntity<List<TaskDto>> getForSubject(@PathVariable Long id) {
        return new ResponseEntity<>(taskMapper.toTaskDtoList(taskService.findBySubject(id)), HttpStatus.OK);
    }

    @Operation(summary = "Get task by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskDto.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(taskService.findById(id).map(taskMapper::toTaskDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping("/{id}/grades")
    public ResponseEntity<List<StudentGradeDto>> findByTaskId(@PathVariable Long id) {

        return ResponseEntity.ok(studentGradeMapper.toStudentGradeDtoList(studentGradeService.findAllByTaskId(id)));
    }
}
