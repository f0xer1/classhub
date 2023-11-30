package com.classhub.api.controller;

import com.classhub.api.model.dto.Admin.AdministratorDto;
import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.dto.Student.StudentDto;
import com.classhub.api.model.dto.Student.StudentUpdateDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectDto;
import com.classhub.api.model.mapper.StudentMapper;
import com.classhub.api.model.mapper.TeachingSubjectMapper;
import com.classhub.api.service.StudentService;
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
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final TeachingSubjectMapper teachingSubjectMapper;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') ")
    @Operation(summary = "Update student by username", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PutMapping("/{username}")
    public ResponseEntity<StudentDto> editStudent(@PathVariable String username, @RequestBody StudentUpdateDto updateDto) {
        var student = studentService.editStudent(studentMapper.toStudent(updateDto), username);
        return new ResponseEntity<>(studentMapper.toStudentDTO(student), HttpStatus.OK);
    }

    @Operation(summary = "Get student by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable Long id) {
        return ResponseEntity.of(studentService.findById(id).map(studentMapper::toStudentDTO));
    }

    @Operation(summary = "Get subjects by student id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TeachingSubjectDto.class)))),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER') or #studentId == authentication.principal.id ")
    @GetMapping("/{studentId}/subjects")
    public ResponseEntity<List<TeachingSubjectDto>> getSubjectsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(teachingSubjectMapper.toTeachingSubjectDtoList(studentService.getSubjectsByStudentId(studentId)));
    }
}
