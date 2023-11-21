package com.classhub.api.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.classhub.api.config.JWTTokenProvider;
import com.classhub.api.exeption.*;
import com.classhub.api.model.users.Administrator;
import com.classhub.api.model.mapper.StudentMapper;
import com.classhub.api.model.mapper.TeacherMapper;
import com.classhub.api.model.users.Student;
import com.classhub.api.model.users.Teacher;
import com.classhub.api.model.mapper.AdministratorMapper;
import com.classhub.api.model.users.User;
import com.classhub.api.repository.UserRepository;
import com.classhub.api.service.AdministratorService;
import com.classhub.api.service.StudentService;
import com.classhub.api.service.TeacherService;
import com.classhub.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JWTTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorService administratorService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final AdministratorMapper administratorMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;


    @Override
    public User signUpForStudent(User user) {
        existsByUsername(user);
        user.setRole("ROLE_STUDENT");
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        userRepository.save(user);
        studentService.createStudent(user.getId());
        return user;
    }

    @Override
    public User signUpForTeacher(User user) {
        existsByUsername(user);
        user.setRole("ROLE_TEACHER");
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        userRepository.save(user);
        teacherService.createTeacher(user.getId());
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User signUpForAdmin(User user) {
        existsByUsername(user);
        user.setRole("ROLE_ADMINISTRATOR");
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        userRepository.save(user);
      administratorService.createAdmin(user.getId());
        return user;
    }

    public void existsByUsername(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(
                    "Username %s is already in use".formatted(user.getUsername()));
        }
    }


    @Override
    public ResponseEntity<Object> getInfoByUsername(String username) {
        var user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username %s not found".formatted(username))
        );
        switch (user.getRole()) {
            case "ROLE_STUDENT":
                Student student = studentService.getStudentById(user.getId())
                        .orElseThrow(() -> new StudentNotFoundException("Student not found"));
                return new ResponseEntity<>(studentMapper.toStudentDTO(student), HttpStatus.OK);
            case "ROLE_TEACHER":
                Teacher teacher = teacherService.getTeacherById(user.getId())
                        .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
                return new ResponseEntity<>(teacherMapper.toTeacherDto(teacher), HttpStatus.OK);
            case "ROLE_ADMINISTRATOR":
                Administrator administrator = administratorService.getAdminById(user.getId())
                        .orElseThrow(() -> new AdministratorNotFoundException("Administrator not found"));
                return new ResponseEntity<>(administratorMapper.toAdministratorDTO(administrator), HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public Optional<DecodedJWT> signIn(String username, String pwd) {
        var user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username %s not found".formatted(username))
        );
        if (!passwordEncoder.matches(pwd, user.getPwd())) {
            throw new InvalidPasswordException("Invalid password");
        }
        return jwtTokenProvider.toDecodedJWT(
                jwtTokenProvider.generateToken(user.getId(), username, user.getRole()));
    }
}
