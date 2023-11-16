package com.classhub.api.model.mapper;


import com.classhub.api.model.Student;
import com.classhub.api.model.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.role", target = "role")
    StudentDto toStudentDTO(Student student);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Student updateStudentFromDTO(StudentDto studentDto, @MappingTarget Student student);

}
