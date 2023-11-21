package com.classhub.api.model.dto.StudentGrade;

import lombok.Data;

@Data
public class StudentGradeDto  {
    private Long id;
    private int grade;
    private Long taskId;
    private Long studentId;
}
