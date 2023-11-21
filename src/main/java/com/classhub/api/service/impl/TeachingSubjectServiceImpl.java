package com.classhub.api.service.impl;

import com.classhub.api.exeption.TeacherNotFoundException;
import com.classhub.api.exeption.TeachingSubjectAlreadyExistsException;
import com.classhub.api.model.links.StudentSubject;
import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.model.users.Student;
import com.classhub.api.model.users.Teacher;
import com.classhub.api.repository.StudentSubjectRepository;
import com.classhub.api.repository.SubjectRepository;
import com.classhub.api.repository.TeachingPeriodRepository;
import com.classhub.api.repository.TeachingSubjectRepository;
import com.classhub.api.service.TeachingSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeachingSubjectServiceImpl implements TeachingSubjectService {
    private final TeachingSubjectRepository teachingSubjectRepository;
    private final SubjectRepository subjectRepository;
    private final TeachingPeriodRepository teachingPeriodRepository;
    private final StudentSubjectRepository studentSubjectRepository;
    @Override
    public TeachingSubject addTeachingSubject(TeachingSubject teachingSubject) {
        if (teachingSubjectRepository.existsBySubjectAndTeachingPeriod(subjectRepository.findById(teachingSubject.getSubject().getId()),
                teachingPeriodRepository.findById(teachingSubject.getTeachingPeriod().getId()))){
            throw new TeachingSubjectAlreadyExistsException(
                    "TeachingSubject  is already in use");
        }
        return  teachingSubjectRepository.save(teachingSubject);
    }

    @Override
    public Optional<TeachingSubject> findById(Long id) {
        return teachingSubjectRepository.findById(id);
    }

    @Override
    public List<TeachingSubject> getAllTeachingSubject() {
        return teachingSubjectRepository.findAll();
    }

    @Override
    public List<Teacher> getTeachersBySubjectId(Long subjectId) {
        Optional<TeachingSubject> teachingSubjectOptional = teachingSubjectRepository.findById(subjectId);
        if (teachingSubjectOptional.isPresent()) {
            TeachingSubject teachingSubject = teachingSubjectOptional.get();
            return new ArrayList<>(teachingSubject.getTeachers());
        } else {
            throw new TeacherNotFoundException("TeachingSubject with ID " + subjectId + " not found");
        }
    }

    @Override
    public List<Student> getStudentBySubjectId(Long subjectId) {
        List<StudentSubject> studentSubjects = studentSubjectRepository.findAllByTeachingSubject(
                teachingSubjectRepository.findById(subjectId));
        List<Student> students = new ArrayList<>();

        for (StudentSubject studentSubject : studentSubjects) {
            students.add(studentSubject.getStudent());
        }

        return students;
    }


}
