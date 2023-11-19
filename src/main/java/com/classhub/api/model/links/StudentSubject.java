package com.classhub.api.model.links;

import com.classhub.api.model.subjects.TeachingSubject;
import com.classhub.api.model.users.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student_subject", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"teaching_subject_id", "student_id"})
})
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "teaching_subject_id")
    private TeachingSubject teachingSubject;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "attestation1")
    private boolean attestation1;
    @Column(name = "attestation2")
    private boolean attestation2;
}
