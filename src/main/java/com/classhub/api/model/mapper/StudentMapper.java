package com.classhub.api.model.mapper;


import com.classhub.api.model.dto.Student.StudentDto;
import com.classhub.api.model.dto.Student.StudentUpdateDto;
import com.classhub.api.model.users.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.role", target = "role")
    StudentDto toStudentDTO(Student student);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Student toStudent(StudentUpdateDto updateDto);

}
