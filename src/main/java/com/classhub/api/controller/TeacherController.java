package com.classhub.api.controller;

import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.dto.Teacher.TeacherDto;
import com.classhub.api.model.dto.Teacher.TeacherUpdateDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectDto;
import com.classhub.api.model.mapper.TeacherMapper;
import com.classhub.api.model.mapper.TeachingSubjectMapper;
import com.classhub.api.service.TeacherService;
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

@RestController
@RequestMapping(path = "/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    private final TeacherMapper teacherMapper;
    private final TeachingSubjectMapper teachingSubjectMapper;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or(hasRole('ROLE_TEACHER') and #username == authentication.principal.username)")
    @Operation(summary = "Update teacher by username", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })

    @PutMapping("/{username}")
    public ResponseEntity<TeacherDto> editTeacher(@PathVariable String username, @RequestBody TeacherUpdateDto updateDto) {
        var teacher = teacherService.editTeacher(teacherMapper.toTeacher(updateDto), username);
        return new ResponseEntity<>(teacherMapper.toTeacherDto(teacher), HttpStatus.OK);
    }

    @Operation(summary = "Get teacher by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findTeacherById(@PathVariable Long id) {
        return ResponseEntity.of(teacherService.getTeacherById(id).map(teacherMapper::toTeacherDto));
    }
    @Operation(summary = "Get subjects by teacher id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TeachingSubjectDto.class)))),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or(hasRole('ROLE_TEACHER') and #teacherId == authentication.principal.id)")

    @GetMapping("/{teacherId}/subjects")
    public ResponseEntity<List<TeachingSubjectDto>> getSubjectsByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teachingSubjectMapper.toTeachingSubjectDtoList(teacherService.getSubjectsByTeacherId(teacherId)));
    }

}
