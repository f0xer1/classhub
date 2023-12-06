package com.classhub.api.controller;

import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.dto.Student.StudentDto;
import com.classhub.api.model.dto.StudentGrade.StudentGradeCreationDto;
import com.classhub.api.model.dto.StudentGrade.StudentGradeDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectDto;
import com.classhub.api.model.mapper.StudentGradeMapper;
import com.classhub.api.service.StudentGradeService;
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
@RequestMapping(path = "/grades")
@RequiredArgsConstructor
public class StudentGradeController {
    private final StudentGradeService studentGradeService;
    private final StudentGradeMapper studentGradeMapper;

    @Operation(summary = "Create student grade", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentGradeDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @PostMapping()
    public ResponseEntity<StudentGradeDto> addGrades(@RequestBody StudentGradeCreationDto studentGradeCreationDto) {
        var grade = studentGradeService.addGrade(studentGradeMapper.toStudentGrade(studentGradeCreationDto));
        return new ResponseEntity<>(studentGradeMapper.toStudentGradeDto(grade), HttpStatus.CREATED);
    }

    @Operation(summary = "Get student grade by student id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = StudentDto.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER') or #id == authentication.principal.id")
    @GetMapping("/student/{id}")
    public ResponseEntity<List<StudentGradeDto>> getGradesForStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentGradeMapper.toStudentGradeDtoList(studentGradeService.getGradesForStudent(id)), HttpStatus.OK);
    }

    @Operation(summary = "Get student grade by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentGradeDto.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    public ResponseEntity<StudentGradeDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(studentGradeService.findById(id).map(studentGradeMapper::toStudentGradeDto));
    }

}
