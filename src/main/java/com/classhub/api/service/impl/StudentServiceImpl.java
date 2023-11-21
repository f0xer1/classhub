package com.classhub.api.service.impl;

import com.classhub.api.exeption.ChangeException;
import com.classhub.api.exeption.StudentNotFoundException;
import com.classhub.api.exeption.UserNotFoundException;
import com.classhub.api.model.links.StudentSubject;
import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.model.users.Student;
import com.classhub.api.repository.StudentSubjectRepository;
import com.classhub.api.repository.StudentsRepository;
import com.classhub.api.repository.UserRepository;
import com.classhub.api.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentsRepository studentsRepository;
    private final UserRepository userRepository;
    private  final StudentSubjectRepository studentSubjectRepository;

    @Override
    public void createStudent(Long id) {
            studentsRepository.save(new Student(id));
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentsRepository.findById(id);
    }

    @Override
    public Student getStudentByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> studentsRepository.findById(user.getId()))
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with username %s not found", username)));
    }

    @Override
    public Student  editStudent(Student student, String username) {
        try {
            Student existingStudent = getStudentByUsername(username);
            existingStudent.setPatronymic(student.getPatronymic());
            existingStudent.setLast_name(student.getLast_name());
            existingStudent.setFirst_name(student.getFirst_name());
            existingStudent.setFaculty(student.getFaculty());
            existingStudent.setCluster(student.getCluster());
            studentsRepository.save(existingStudent);
            return existingStudent;
        } catch (EntityNotFoundException e) {
            throw new StudentNotFoundException("Student with username " + username + " not found");
        } catch (Exception e) {
            throw new ChangeException("Error editing administrator");
        }
    }

    @Override
    public Optional<Student> findById(Long studentId) {
        return studentsRepository.findById(studentId);
    }

    @Override
    public List<TeachingSubject> getSubjectsByStudentId(Long studentId) {
        List<StudentSubject> studentSubjects = studentSubjectRepository.findAllByStudent(studentsRepository.findById(studentId) );
        List<TeachingSubject> teachingSubjects = new ArrayList<>();

        for (StudentSubject studentSubject : studentSubjects) {
            teachingSubjects.add(studentSubject.getTeachingSubject());
        }

        return teachingSubjects;
    }

}

