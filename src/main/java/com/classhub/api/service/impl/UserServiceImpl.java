package com.classhub.api.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.classhub.api.config.JWTTokenProvider;
import com.classhub.api.exeption.InvalidPasswordException;
import com.classhub.api.exeption.RoleNotFoundException;
import com.classhub.api.exeption.UserAlreadyExistsException;
import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.model.user.User;
import com.classhub.api.repository.UserRepository;
import com.classhub.api.service.AdministratorService;
import com.classhub.api.service.StudentService;
import com.classhub.api.service.TeacherService;
import com.classhub.api.service.UserService;
import lombok.RequiredArgsConstructor;
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


    public User signUpForAdmin(User user) {
        user.setRole("ROLE_ADMINISTRATOR");
        userRepository.save(user);
        administratorService.createAdmin(user.getId());
        return user;
    }

    public User signUpForStudent(User user) {
        user.setRole("ROLE_STUDENT");
        userRepository.save(user);
        studentService.createStudent(user.getId());
        return user;
    }

    public User signUpForTeacher(User user) {
        user.setRole("ROLE_TEACHER");
        userRepository.save(user);
        teacherService.createTeacher(user.getId());
        return user;
    }



    @Override
    public User signUpForUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(
                    "Username %s is already in use".formatted(user.getUsername()));
        }
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        String role = user.getRole();
        return switch (role != null ? role.toLowerCase() : "admin") {
            case "student" -> signUpForStudent(user);
            case "teacher" -> signUpForTeacher(user);
            case "admin"  -> signUpForAdmin(user);
            default -> throw new RoleNotFoundException("Invalid %s role".formatted(role));
        };
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
