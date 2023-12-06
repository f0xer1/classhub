package com.classhub.api.controller;


import com.classhub.api.model.dto.User.UserDto;
import com.classhub.api.model.mapper.UserMapper;
import com.classhub.api.service.UserService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Get user settings by username", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{username}")
    public ResponseEntity<Object> getUserSettings(@PathVariable String username) {
        return new ResponseEntity<>(userService.getInfoByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Get user by id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        return ResponseEntity.of(userService.findById(id).map(userMapper::toUserDTO));
    }

    @Operation(summary = "Get teaching subject", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping()
    public ResponseEntity<List<UserDto>> allTeachingSubject() {
        return new ResponseEntity<>(userMapper.toUserDTOList(userService.getAllUsers()), HttpStatus.OK);
    }

}
