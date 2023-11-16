package com.classhub.api.service.impl;

import com.classhub.api.exeption.ChangeException;
import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.model.mapper.StudentMapper;
import com.classhub.api.model.Student;
import com.classhub.api.model.dto.StudentDto;
import com.classhub.api.repository.StudentsRepository;
import com.classhub.api.repository.UserRepository;
import com.classhub.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentsRepository studentsRepository;
    private final StudentMapper studentMapper;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> createStudent(Long id) {
        try {
            studentsRepository.save(new Student(id));
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentsRepository.findById(id);
    }

    @Override
    public Student getStudentByUsername(String username) {
        Long id = userRepository.findByUsername(username).get().getId();
        if (!studentsRepository.existsById(id)) {
            throw new UserNotFoundException(
                    "User with username %s not found".formatted(username));
        }
        return studentsRepository.findById(id).get();
    }

    @Override
    public String editStudent(StudentDto studentDto) {
        try {
            Student existingStudent = getStudentByUsername(studentDto.getUsername());
            Student updatedStudent = studentMapper.updateStudentFromDTO(studentDto, existingStudent);
            studentsRepository.save(updatedStudent);
            return "Success";
        } catch (UserNotFoundException e) {
            return "User not found: " + e.getMessage();
        } catch (ChangeException e) {
            return "Change error: " + e.getMessage();
        } catch (Exception e) {
            return "An error occurred while processing the request";
        }
    }

}

