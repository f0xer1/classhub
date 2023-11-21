package com.classhub.api.model.dto.StudentGrade;

import lombok.Data;

@Data

public class StudentGradeCreationDto {
    private int grade;
    private Long taskId;
    private Long studentId;
}
