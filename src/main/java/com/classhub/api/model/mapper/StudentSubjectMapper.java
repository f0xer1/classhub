package com.classhub.api.model.mapper;

import com.classhub.api.model.dto.StudentSubject.StudentSubjectCreationDto;
import com.classhub.api.model.dto.StudentSubject.StudentSubjectDto;
import com.classhub.api.model.links.StudentSubject;
import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.model.users.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentSubjectMapper {

    @Mapping(target = "teachingSubject", source = "teachingSubjectId", qualifiedByName = "mapToTeachingSubject")
    @Mapping(target = "student", source = "studentId", qualifiedByName = "mapToStudent")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attestation2", source = "attestation2")
    @Mapping(target = "attestation1", source = "attestation1")
    StudentSubject toStudentSubject(StudentSubjectCreationDto studentSubjectCreationDto);

    @Named("mapToTeachingSubject")
    static TeachingSubject mapToTeachingSubject(Long teachingSubjectId) {
        if (teachingSubjectId == null) {
            return null;
        }
        TeachingSubject teachingSubject = new TeachingSubject();
        teachingSubject.setId(teachingSubjectId);
        return teachingSubject;
    }

    @Named("mapToStudent")
    static Student mapToStudent(Long studentId) {
        if (studentId == null) {
            return null;
        }
        Student student = new Student();
        student.setId(studentId);
        return student;
    }
    @Mapping(target = "teachingSubjectId", source = "teachingSubject.id")
    @Mapping(target = "studentId", source = "student.id")
    StudentSubjectDto toStudentSubjectDto(StudentSubject studentSubject);


    List<StudentSubjectDto> toStudentSubjectDtoList(List<StudentSubject> studentSubjectForStudent);
}
