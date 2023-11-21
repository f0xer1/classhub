package com.classhub.api.model.dto.Student;

import lombok.Data;

@Data
public class StudentUpdateDto {
    private String first_name;
    private String last_name;
    private String patronymic;
    private String cluster;
    private String faculty;
}
