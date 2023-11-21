package com.classhub.api.controller;

import com.classhub.api.model.dto.Admin.AdministratorDto;
import com.classhub.api.model.dto.Admin.AdministratorUpdateDto;
import com.classhub.api.model.dto.Student.StudentDto;
import com.classhub.api.model.dto.Student.StudentUpdateDto;
import com.classhub.api.model.dto.Teacher.TeacherDto;
import com.classhub.api.model.dto.Teacher.TeacherUpdateDto;
import com.classhub.api.model.mapper.AdministratorMapper;
import com.classhub.api.model.mapper.StudentMapper;
import com.classhub.api.model.mapper.TeacherMapper;
import com.classhub.api.service.AdministratorService;
import com.classhub.api.service.StudentService;
import com.classhub.api.service.TeacherService;
import com.classhub.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final AdministratorService administratorService;
    private final AdministratorMapper administratorMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;

    @GetMapping("/{username}")
    public ResponseEntity<Object> getUserSettings(@PathVariable String username) {
        return userService.getInfoByUsername(username);
    }

    @PutMapping("/admins/{username}")
    public ResponseEntity<AdministratorDto> editAdmin(@PathVariable String username, @RequestBody AdministratorUpdateDto updateDto) {
        var admin = administratorService.editAdmin(administratorMapper.toAdministrator(updateDto), username);
        return new ResponseEntity<>(administratorMapper.toAdministratorDTO(admin), HttpStatus.OK);
    }

    @PutMapping("/students/{username}")
    public ResponseEntity<StudentDto> editStudent(@PathVariable String username, @RequestBody StudentUpdateDto updateDto) {
        var student = studentService.editStudent(studentMapper.toStudent(updateDto), username);
        return new ResponseEntity<>(studentMapper.toStudentDTO(student), HttpStatus.OK);
    }

    @PutMapping("/teachers/{username}")
    public ResponseEntity<TeacherDto> editTeacher(@PathVariable String username, @RequestBody TeacherUpdateDto updateDto) {
        var teacher = teacherService.editTeacher(teacherMapper.toTeacher(updateDto), username);
        return new ResponseEntity<>(teacherMapper.toTeacherDto(teacher), HttpStatus.OK);
    }
}
