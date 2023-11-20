package com.classhub.api.controller;

import com.classhub.api.model.dto.AdministratorDto;
import com.classhub.api.model.dto.StudentDto;
import com.classhub.api.model.dto.TeacherDto;
import com.classhub.api.service.AdministratorService;
import com.classhub.api.service.StudentService;
import com.classhub.api.service.TeacherService;
import com.classhub.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/users")
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
    @PutMapping("/admins/{username}")
    public ResponseEntity<String> editAdmin(@PathVariable String username, @RequestBody AdministratorDto administratorDto) {//пофіксити метод
        return  administratorService.editAdmin(administratorDto);
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/students/{username}")
    public ResponseEntity<String> editStudent(@PathVariable String username, @RequestBody StudentDto studentDto) {//пофіксити метод

        return studentService.editStudent(studentDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or #teacherDto.username == principal.username")
    @PutMapping("/teachers/{username}")
    public ResponseEntity<String> editTeacher(@PathVariable String username, @RequestBody TeacherDto teacherDto) {//пофіксити метод
        return teacherService.editTeacher(teacherDto);
    }
}
