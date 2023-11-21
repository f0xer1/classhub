package com.classhub.api.controller;

import com.classhub.api.model.dto.User.UserCreationDto;
import com.classhub.api.model.dto.User.UserDto;
import com.classhub.api.model.mapper.UserMapper;
import com.classhub.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/sign-student")
    public ResponseEntity<UserDto> createStudent(@RequestBody @Valid UserCreationDto userDto) {
        var newUser = userService.signUpForStudent(userMapper.toUser(userDto));
        return new ResponseEntity<>(userMapper.toUserDTO(newUser), HttpStatus.CREATED);
    }

    @PostMapping("/sign-teacher")
    public ResponseEntity<UserDto> createTeacher(@RequestBody @Valid UserCreationDto userDto) {
        var newUser = userService.signUpForTeacher(userMapper.toUser(userDto));
        return new ResponseEntity<>(userMapper.toUserDTO(newUser), HttpStatus.CREATED);
    }
}