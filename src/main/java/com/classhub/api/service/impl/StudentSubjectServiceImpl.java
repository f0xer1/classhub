package com.classhub.api.service.impl;

import com.classhub.api.model.dto.StudentSubjectDto;
import com.classhub.api.model.links.StudentSubject;
import com.classhub.api.model.users.Student;
import com.classhub.api.repository.StudentSubjectRepository;
import com.classhub.api.service.StudentService;
import com.classhub.api.service.StudentSubjectService;
import com.classhub.api.service.TeachingSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentSubjectServiceImpl implements StudentSubjectService {
    private final StudentSubjectRepository subjectRepository;
    private final StudentService studentService;
    private final TeachingSubjectService teachingSubjectService;

    @Override
    public String addStudentSubject(StudentSubjectDto studentSubjectDto) {
        subjectRepository.save(mapToStudentSubject(studentSubjectDto));
        return "ok";
    }

    @Override
    public List<StudentSubject> getStudentSubjectForStudent(Long studentId) {
        Student student = studentService.findById(studentId).get();
        return subjectRepository.findAllByStudent(student);
    }

    public StudentSubject mapToStudentSubject(StudentSubjectDto studentSubjectDto) {
        StudentSubject subject = new StudentSubject();
        subject.setAttestation1(studentSubjectDto.isAttestation1());
        subject.setAttestation2(studentSubjectDto.isAttestation2());
        subject.setStudent(studentService.findById(studentSubjectDto.getStudentId()).get());
        subject.setTeachingSubject(teachingSubjectService.findById(studentSubjectDto.getTeachingSubjectId()).get());
        return subject;
    }
}
