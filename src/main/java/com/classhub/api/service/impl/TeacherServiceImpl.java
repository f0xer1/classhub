package com.classhub.api.service.impl;

import com.classhub.api.exeption.ChangeException;
import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.model.mapper.TeacherMapper;
import com.classhub.api.model.Teacher;
import com.classhub.api.model.dto.TeacherDto;
import com.classhub.api.repository.TeachersRepository;
import com.classhub.api.repository.UserRepository;
import com.classhub.api.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private  final TeachersRepository teachersRepository;
    private  final TeacherMapper teacherMapper;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<String> createTeacher(Long id) {
        try {
            teachersRepository.save(new Teacher(id));
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return teachersRepository.findById(id);
    }

    @Override
    public Teacher getTeacherByUsername(String username) {
        Long id =userRepository.findByUsername(username).get().getId();
        if (!teachersRepository.existsById(id)) {
            throw new UserNotFoundException(
                    "User with username %s not found".formatted(username));
        }
        return teachersRepository.findById(id).get();
    }

    @Override
    public String editTeacher(TeacherDto teacherDto) {
        try {
            Teacher existingTeacher = getTeacherByUsername(teacherDto.getUsername());
            Teacher updatedTeacher = teacherMapper.updateTeacherFromDTO(teacherDto, existingTeacher);
            teachersRepository.save(updatedTeacher);
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
