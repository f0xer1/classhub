package com.classhub.api.controller;

import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.dto.Task.TaskDto;
import com.classhub.api.model.subjects.Subject;
import com.classhub.api.service.SubjectService;
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
@RequestMapping(path = "/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @Operation(summary = "Create new subject", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Subject.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') ")
    @PostMapping()
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        return new ResponseEntity<>(subjectService.addSubject(subject), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all subject by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Subject.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping()
    public ResponseEntity<List<Subject>> getAllSubject() {
        return new ResponseEntity<>(subjectService.getAllSubject(), HttpStatus.OK);
    }

    @Operation(summary = "Get subject by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Subject.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_TEACHER')")
    @GetMapping("/{id}")
    public ResponseEntity<Subject> findById(@PathVariable Long id) {
        return ResponseEntity.of(subjectService.findById(id));
    }


}
