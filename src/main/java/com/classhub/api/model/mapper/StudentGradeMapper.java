package com.classhub.api.model.mapper;

import com.classhub.api.model.dto.StudentGrade.StudentGradeCreationDto;
import com.classhub.api.model.dto.StudentGrade.StudentGradeDto;
import com.classhub.api.model.links.StudentGrade;
import com.classhub.api.model.subjects.Task;
import com.classhub.api.model.users.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentGradeMapper {

    @Mapping(target = "task", source = "taskId", qualifiedByName = "mapToTask")
    @Mapping(target = "student", source = "studentId", qualifiedByName = "mapToStudent")
    @Mapping(target = "id", ignore = true)
    StudentGrade toStudentGrade(StudentGradeCreationDto studentGradeCreationDto);

    @Mapping(target = "taskId", source = "task.id")
    @Mapping(target = "studentId", source = "student.id")
    StudentGradeDto toStudentGradeDto(StudentGrade studentGrade);

    @Named("mapToTask")
    static Task mapToTask(Long taskId) {
        if (taskId == null) {
            return null;
        }
        Task task = new Task();
        task.setId(taskId);
        return task;
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

    List<StudentGradeDto> toStudentGradeDtoList(List<StudentGrade> gradesForStudent);
}