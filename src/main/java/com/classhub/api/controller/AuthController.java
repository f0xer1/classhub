package com.classhub.api.controller;

import com.classhub.api.controller.auth.JWTToken;
import com.classhub.api.model.dto.ExceptionResponse;
import com.classhub.api.model.mapper.JWTTokenMapper;
import com.classhub.api.model.dto.User.UserCreationDto;
import com.classhub.api.model.dto.User.UserDto;
import com.classhub.api.model.mapper.UserMapper;
import com.classhub.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final JWTTokenMapper jwtTokenMapper;
    @Operation(summary = "Sign up new administrator", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUpForAdmin(@RequestBody @Valid UserCreationDto userDto) {
        var newUser = userService.signUpForAdmin(userMapper.toUser(userDto));
        return new ResponseEntity<>(userMapper.toUserDTO(newUser), HttpStatus.CREATED);
    }
    @Operation(summary = "Sign in user", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = JWTToken.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/sign-in")
    public ResponseEntity<JWTToken> signIn(@RequestBody @Valid UserCreationDto userDto) {
        return ResponseEntity.of(userService
                .signIn(userDto.getUsername(), userDto.getPwd())
                .map(jwtTokenMapper::toPayload));
    }

}
