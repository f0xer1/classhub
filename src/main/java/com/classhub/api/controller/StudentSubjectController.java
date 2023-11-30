package com.classhub.api.controller;

import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.dto.Student.StudentDto;
import com.classhub.api.model.dto.StudentGrade.StudentGradeDto;
import com.classhub.api.model.dto.StudentSubject.StudentSubjectCreationDto;
import com.classhub.api.model.dto.StudentSubject.StudentSubjectDto;
import com.classhub.api.model.mapper.StudentSubjectMapper;
import com.classhub.api.service.StudentSubjectService;
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
@RequestMapping(path = "/student-subjects")
@RequiredArgsConstructor
public class StudentSubjectController {
    private final StudentSubjectService studentSubjectService;
    private final StudentSubjectMapper studentSubjectMapper;

    @Operation(summary = "Create student subject", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentSubjectDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @PostMapping()
    public ResponseEntity<StudentSubjectDto> addStudentSubject(@RequestBody StudentSubjectCreationDto studentSubjectCreationDto) {
        var studentSubject = studentSubjectService.addStudentSubject(
                studentSubjectMapper.toStudentSubject(studentSubjectCreationDto));
        return new ResponseEntity<>(studentSubjectMapper.toStudentSubjectDto(studentSubject), HttpStatus.OK);
    }

    @Operation(summary = "Get student subject by student id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = StudentSubjectDto.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER') or #id == authentication.principal.id")

    @GetMapping("/student/{id}")
    public ResponseEntity<List<StudentSubjectDto>> getStudentSubjectForStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentSubjectMapper.toStudentSubjectDtoList(
                studentSubjectService.getStudentSubjectForStudent(id)), HttpStatus.OK);
    }
    @Operation(summary = "Get student subject by subject id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = StudentSubjectDto.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping("/subject/{id}")
    public ResponseEntity<List<StudentSubjectDto>> getStudentSubjectForSubject(@PathVariable Long id) {
        return new ResponseEntity<>(studentSubjectMapper.toStudentSubjectDtoList(
                studentSubjectService.getStudentSubjectForSubject(id)), HttpStatus.OK);
    }
    @Operation(summary = "Get student subject by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentGradeDto.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentSubjectDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(studentSubjectService.findById(id).map(studentSubjectMapper::toStudentSubjectDto));
    }

}
