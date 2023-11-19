package com.classhub.api.model.dto;

import lombok.Data;

@Data
public class StudentSubjectDto {
    private Long teachingSubjectId;
    private Long studentId;
    private boolean attestation1;
    private boolean attestation2;


}
