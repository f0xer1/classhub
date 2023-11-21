package com.classhub.api.model.dto.StudentSubject;

import lombok.Data;

@Data
public class StudentSubjectCreationDto {
    private Long teachingSubjectId;
    private Long studentId;
    private boolean attestation1;
    private boolean attestation2;
}
