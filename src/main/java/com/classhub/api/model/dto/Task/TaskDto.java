package com.classhub.api.model.dto.Task;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Long teachingSubjectId;
}
