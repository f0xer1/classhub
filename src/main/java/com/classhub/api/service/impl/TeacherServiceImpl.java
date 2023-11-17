package com.classhub.api.service.impl;

import com.classhub.api.exeption.ChangeException;
import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.model.mapper.TeacherMapper;
import com.classhub.api.model.users.Teacher;
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
    public ResponseEntity<String>  editTeacher(TeacherDto teacherDto) {
        try {
            Teacher existingTeacher = getTeacherByUsername(teacherDto.getUsername());
            Teacher updatedTeacher = teacherMapper.updateTeacherFromDTO(teacherDto, existingTeacher);
            teachersRepository.save(updatedTeacher);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ChangeException e) {
            return new ResponseEntity<>("Change error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
