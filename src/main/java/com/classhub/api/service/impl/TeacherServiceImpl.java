package com.classhub.api.service.impl;

import com.classhub.api.exeption.ChangeException;
import com.classhub.api.exeption.TeacherNotFoundException;
import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.model.users.Teacher;
import com.classhub.api.repository.TeachersRepository;
import com.classhub.api.repository.UserRepository;
import com.classhub.api.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeachersRepository teachersRepository;
    private final UserRepository userRepository;

    @Override
    public void createTeacher(Long id) {
            teachersRepository.save(new Teacher(id));
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return teachersRepository.findById(id);
    }

    @Override
    public Teacher getTeacherByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> teachersRepository.findById(user.getId()))
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with username %s not found", username)));
    }

    @Override
    public Teacher editTeacher(Teacher teacher, String username) {
        try {
            Teacher existingTeacher = getTeacherByUsername(username);
            existingTeacher.setPatronymic(teacher.getPatronymic());
            existingTeacher.setLast_name(teacher.getLast_name());
            existingTeacher.setFirst_name(teacher.getFirst_name());
            teachersRepository.save(existingTeacher);
            return existingTeacher;
        } catch (EntityNotFoundException e) {
            throw new TeacherNotFoundException("Teacher with username " + username + " not found");
        } catch (Exception e) {
            throw new ChangeException("Error editing administrator");
        }

    }

    @Override
    public List<TeachingSubject> getSubjectsByTeacherId(Long teacherId) {
        Optional<Teacher> teacherOptional = teachersRepository.findById(teacherId);

        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();
            return new ArrayList<>(teacher.getVoting());
        } else {

            throw new EntityNotFoundException("Teacher with ID " + teacherId + " not found");
        }
    }
}
