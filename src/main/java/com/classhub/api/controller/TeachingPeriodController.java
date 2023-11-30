package com.classhub.api.controller;

import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.dto.Task.TaskDto;
import com.classhub.api.model.subjects.TeachingPeriod;
import com.classhub.api.service.TeachingPeriodService;
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
@RequestMapping(path = "/teaching-periods")
@RequiredArgsConstructor
public class TeachingPeriodController {
    private final TeachingPeriodService teachingPeriodService;
    @Operation(summary = "Create teaching period task", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TeachingPeriod.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') ")
    @PostMapping()
    public ResponseEntity<TeachingPeriod> addTeachingPeriod(@RequestBody TeachingPeriod teachingPeriod) {
        return new ResponseEntity<>(teachingPeriodService.addTeachingPeriod(teachingPeriod), HttpStatus.CREATED);
    }
    @Operation(summary = "Get all teaching period by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema( schema = @Schema(implementation = TeachingPeriod.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<TeachingPeriod>> getAllTeachingPeriod() {
        return new ResponseEntity<>(teachingPeriodService.getAllTeachingPeriod(), HttpStatus.OK);
    }    @Operation(summary = "Get teaching period by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TeachingPeriod.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TeachingPeriod> findById(@PathVariable Long id){
        return ResponseEntity.of(teachingPeriodService.findById(id));
    }
}
