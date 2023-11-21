package com.classhub.api.model.dto.StudentSubject;

import lombok.Data;

@Data
public class StudentSubjectDto {
    private Long id;
    private Long teachingSubjectId;
    private Long studentId;
    private boolean attestation1;
    private boolean attestation2;
}
