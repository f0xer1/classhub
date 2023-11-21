package com.classhub.api.model.dto.Teacher;

import lombok.Data;

@Data
public class TeacherDto {
    private Long id;
    private String username;
    private String role;
    private String first_name;
    private String last_name;
    private String patronymic;
}
