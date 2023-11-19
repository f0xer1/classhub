package com.classhub.api.model.dto;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Long teaching_subject_id;
}
