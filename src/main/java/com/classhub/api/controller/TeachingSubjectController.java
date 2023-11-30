package com.classhub.api.controller;

import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.dto.Student.StudentDto;
import com.classhub.api.model.dto.Task.TaskDto;
import com.classhub.api.model.dto.Teacher.TeacherDto;
import com.classhub.api.model.dto.TeacherSubject.TeacherSubjectDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectCreationDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectDto;
import com.classhub.api.model.mapper.StudentMapper;
import com.classhub.api.model.mapper.TeacherMapper;
import com.classhub.api.model.mapper.TeachingSubjectMapper;
import com.classhub.api.model.subjects.TeachingPeriod;
import com.classhub.api.service.TeachingSubjectService;
import com.classhub.api.service.TeachingSubjectsTeachersService;
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
@RequestMapping(path = "/teaching-subjects")
@RequiredArgsConstructor
public class TeachingSubjectController {
    private final TeachingSubjectService teachingSubjectService;
    private final TeachingSubjectsTeachersService teachingSubjectsTeachersService;

    private final TeachingSubjectMapper teachingSubjectMapper;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    @Operation(summary = "Create teaching subject", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TeachingSubjectDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PostMapping()
    public ResponseEntity<TeachingSubjectDto> addTeachingSubject(@RequestBody TeachingSubjectCreationDto teachingSubjectDto) {
        var teachingSubject = teachingSubjectService.addTeachingSubject(
                teachingSubjectMapper.toTeachingSubject(teachingSubjectDto));

        return new ResponseEntity<>(teachingSubjectMapper.toTeachingSubjectDto(teachingSubject), HttpStatus.CREATED);
    }

    @Operation(summary = "Get teaching subject", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TeachingSubjectDto.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping()
    public ResponseEntity<List<TeachingSubjectDto>> allTeachingSubject() {
        return new ResponseEntity<>(teachingSubjectMapper.toTeachingSubjectDtoList(teachingSubjectService.getAllTeachingSubject()), HttpStatus.OK);
    }

    @Operation(summary = "Add teacher to teaching subject", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TeacherSubjectDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PostMapping("/teacher")
    public ResponseEntity<TeacherSubjectDto> addTeacherToSubject(@RequestBody TeacherSubjectDto teacherSubjectDto) {
        teachingSubjectsTeachersService.add(teacherSubjectDto.getTeacherId(), teacherSubjectDto.getSubjectId());
        return new ResponseEntity<>(teacherSubjectDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get teaching subject by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TeachingSubjectDto.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TeachingSubjectDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(teachingSubjectService.findById(id).map(teachingSubjectMapper::toTeachingSubjectDto));
    }

    @Operation(summary = "Get teachers by subject id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TeacherDto.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{subjectId}/teachers")
    public ResponseEntity<List<TeacherDto>> getTeachersBySubjectId(@PathVariable Long subjectId) {

        return ResponseEntity.ok(teacherMapper.toTeacherDtoList(teachingSubjectService.getTeachersBySubjectId(subjectId)));
    }

    @Operation(summary = "Get student by subject id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = StudentDto.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{subjectId}/students")
    public ResponseEntity<List<StudentDto>> getStudentsBySubjectId(@PathVariable Long subjectId) {
        return ResponseEntity.ok(studentMapper.toStudentDTOList(teachingSubjectService.getStudentBySubjectId(subjectId)));
    }
}
