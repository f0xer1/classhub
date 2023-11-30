package com.classhub.api.model.dto.TeachingSubject;

import lombok.Data;

import java.util.List;

@Data
public class TeachingSubjectDto {
    private Long id;
    private List<Long> teachers;
    private String name;
    private String description;
    private Integer year;
    private Integer semester;
}
