package com.classhub.api.model.mapper;

import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectCreationDto;
import com.classhub.api.model.dto.TeachingSubject.TeachingSubjectDto;
import com.classhub.api.model.subjects.Subject;
import com.classhub.api.model.subjects.TeachingPeriod;
import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.model.users.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeachingSubjectMapper {

    @Mapping(target = "teachingPeriod", source = "teachingPeriodId", qualifiedByName = "mapToTeachingPeriod")
    @Mapping(target = "subject", source = "subjectId", qualifiedByName = "mapToSubject")


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    TeachingSubject toTeachingSubject(TeachingSubjectCreationDto teachingSubjectCreationDto);

    @Named("mapToTeachingPeriod")
    static TeachingPeriod mapToTeachingPeriod(Long teachingPeriodId) {
        if (teachingPeriodId == null) {
            return null;
        }
        TeachingPeriod teachingPeriod = new TeachingPeriod();
        teachingPeriod.setId(teachingPeriodId);
        return teachingPeriod;
    }

    @Named("mapToSubject")
    static Subject mapToSubject(Long subjectId) {
        if (subjectId == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(subjectId);
        return subject;
    }
    @Named("mapTeachersToIds")
    static List<Long> mapTeachersToIds(Set<Teacher> teachers) {
        return teachers.stream()
                .map(Teacher::getId)
                .collect(Collectors.toList());
    }



    @Mapping(target = "year", source = "teachingPeriod.year")
    @Mapping(target = "semester", source = "teachingPeriod.semester")
    @Mapping(target = "name", source = "subject.name")
    @Mapping(target = "description", source = "subject.description")
    @Mapping(target = "teachers", qualifiedByName = "mapTeachersToIds")
    TeachingSubjectDto toTeachingSubjectDto(TeachingSubject teachingSubject);

    List<TeachingSubjectDto> toTeachingSubjectDtoList(List<TeachingSubject> allTeachingSubject);
}
