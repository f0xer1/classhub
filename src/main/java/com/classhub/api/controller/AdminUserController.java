package com.classhub.api.controller;

import com.classhub.api.model.dto.Admin.AdministratorDto;
import com.classhub.api.model.dto.Admin.AdministratorUpdateDto;
import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.dto.User.UserCreationDto;
import com.classhub.api.model.dto.User.UserDto;
import com.classhub.api.model.mapper.AdministratorMapper;
import com.classhub.api.model.mapper.UserMapper;
import com.classhub.api.service.AdministratorService;
import com.classhub.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admins")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")

public class AdminUserController {
    private final AdministratorService administratorService;
    private final AdministratorMapper administratorMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Sign up new student", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),

            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/sign-student")
    public ResponseEntity<UserDto> createStudent(@RequestBody @Valid UserCreationDto userDto) {
        var newUser = userService.signUpForStudent(userMapper.toUser(userDto));
        return new ResponseEntity<>(userMapper.toUserDTO(newUser), HttpStatus.CREATED);
    }

    @Operation(summary = "Sign up new teacher", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),

            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/sign-teacher")
    public ResponseEntity<UserDto> createTeacher(@RequestBody @Valid UserCreationDto userDto) {
        var newUser = userService.signUpForTeacher(userMapper.toUser(userDto));
        return new ResponseEntity<>(userMapper.toUserDTO(newUser), HttpStatus.CREATED);
    }
    @Operation(summary = "Update administrator by username", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdministratorDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize(" #username == authentication.principal.username")
    @PutMapping("/{username}")
    public ResponseEntity<AdministratorDto> editAdmin(@PathVariable String username, @RequestBody AdministratorUpdateDto updateDto) {
        var admin = administratorService.editAdmin(administratorMapper.toAdministrator(updateDto), username);
        return new ResponseEntity<>(administratorMapper.toAdministratorDTO(admin), HttpStatus.OK);
    }
    @Operation(summary = "Get administrator by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdministratorDto.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdministratorDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(administratorService.getAdminById(id).map(administratorMapper::toAdministratorDTO));
    }
}