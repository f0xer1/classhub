package com.classhub.api.model.subjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teaching_subjects", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"subject_id", "teaching_period_id"})
})
public class Teaching_subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teaching_period_id")
    private Teaching_period teaching_period;

}
