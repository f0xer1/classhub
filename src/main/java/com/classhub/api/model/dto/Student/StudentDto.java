package com.classhub.api.model.dto.Student;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String username;
    private String role;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String cluster;
    private String faculty;
}
