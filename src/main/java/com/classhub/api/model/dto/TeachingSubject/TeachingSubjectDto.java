package com.classhub.api.model.dto.TeachingSubject;

import lombok.Data;

import java.util.List;

@Data
public class TeachingSubjectDto {
    private Long id;
    private Long subjectId;
    private Long teachingPeriodId;
    private List<Long> teachers;
}
