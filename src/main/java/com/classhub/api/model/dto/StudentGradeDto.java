package com.classhub.api.model.dto;

import lombok.Data;

@Data

public class StudentGradeDto {
    private int grade;
    private Long taskId;
    private Long studentId;
}
