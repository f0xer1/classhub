package com.classhub.api.model.mapper;

import com.classhub.api.model.dto.Task.TaskCreationDto;
import com.classhub.api.model.dto.Task.TaskDto;
import com.classhub.api.model.subjects.Task;
import com.classhub.api.model.subjects.TeachingSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teachingSubject", source = "teachingSubjectId", qualifiedByName = "mapToTeachingSubject")
    Task toTask(TaskCreationDto taskCreationDto);

    @Mapping(target = "teachingSubjectId", source = "teachingSubject.id")
    TaskDto toTaskDto(Task task);

    @Named("mapToTeachingSubject")
    static TeachingSubject mapToTeachingSubject(Long teachingSubjectId) {
        if (teachingSubjectId == null) {
            return null;
        }
        TeachingSubject teachingSubject = new TeachingSubject();
        teachingSubject.setId(teachingSubjectId);
        return teachingSubject;
    }

    List<TaskDto> toTaskDtoList(List<Task> bySubject);
}
