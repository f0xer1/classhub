package com.classhub.api.model.mapper;


import com.classhub.api.model.dto.Teacher.TeacherDto;
import com.classhub.api.model.dto.Teacher.TeacherUpdateDto;
import com.classhub.api.model.users.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.role", target = "role")
    TeacherDto toTeacherDto(Teacher teacher);

    @Mapping(target = "voting",ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Teacher toTeacher(TeacherUpdateDto updateDto);


    List<TeacherDto> toTeacherDtoList(List<Teacher> teachersBySubjectId);
}
