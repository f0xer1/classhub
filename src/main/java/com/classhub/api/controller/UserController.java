package com.classhub.api.controller;

import com.classhub.api.model.dto.AdministratorDto;
import com.classhub.api.model.dto.StudentDto;
import com.classhub.api.model.dto.TeacherDto;
import com.classhub.api.service.AdministratorService;
import com.classhub.api.service.StudentService;
import com.classhub.api.service.TeacherService;
import com.classhub.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final AdministratorService administratorService;
    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or #username == principal.username")
    public ResponseEntity<Object> getUserSettings(@PathVariable String username) {
        return userService.getInfoByUsername(username);
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') and #administratorDto.username == principal.username")
    @PostMapping("/editAdmin")
    public ResponseEntity<String> editAdmin(@RequestBody AdministratorDto administratorDto){
        return  administratorService.editAdmin(administratorDto);
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') ")
    @PostMapping("/editStudent")
    public ResponseEntity<String> editStudent(@RequestBody StudentDto studentDto) {

        return studentService.editStudent(studentDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or #teacherDto.username == principal.username")

    @PostMapping("/editTeacher")
    public ResponseEntity<String> editAdmin(@RequestBody TeacherDto teacherDto){
        return teacherService.editTeacher(teacherDto);
    }
}
