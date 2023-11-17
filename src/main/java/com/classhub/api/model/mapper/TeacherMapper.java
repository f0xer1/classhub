package com.classhub.api.model.mapper;


import com.classhub.api.model.users.Teacher;
import com.classhub.api.model.dto.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.role", target = "role")
    TeacherDto toTeacherDto(Teacher teacher);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Teacher updateTeacherFromDTO(TeacherDto teacherDto, @MappingTarget Teacher teacher);
}
