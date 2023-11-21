package com.classhub.api.controller;

import com.classhub.api.model.dto.Admin.AdministratorDto;
import com.classhub.api.model.dto.Admin.AdministratorUpdateDto;
import com.classhub.api.model.dto.User.UserCreationDto;
import com.classhub.api.model.dto.User.UserDto;
import com.classhub.api.model.mapper.AdministratorMapper;
import com.classhub.api.model.mapper.UserMapper;
import com.classhub.api.service.AdministratorService;
import com.classhub.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admins")
@RequiredArgsConstructor
public class AdminUserController {
    private  final AdministratorService administratorService;
    private final AdministratorMapper administratorMapper;
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
    @PutMapping("/{username}")
    public ResponseEntity<AdministratorDto> editAdmin(@PathVariable String username, @RequestBody AdministratorUpdateDto updateDto) {
        var admin = administratorService.editAdmin(administratorMapper.toAdministrator(updateDto), username);
        return new ResponseEntity<>(administratorMapper.toAdministratorDTO(admin), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdministratorDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(administratorService.getAdminById(id).map(administratorMapper::toAdministratorDTO));
    }
}