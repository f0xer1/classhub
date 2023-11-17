package com.classhub.api.model.subjects;

import com.classhub.api.model.users.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "teaching_subjects", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"subject_id", "teaching_period_id"})
})
public class TeachingSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teaching_period_id")
    private TeachingPeriod teaching_period;

    @ManyToMany
    @JoinTable(name = "teaching_subjects_teachers",
            joinColumns = @JoinColumn(name = "teaching_subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teachers_id"))
    private Set<Teacher> teachers = new LinkedHashSet<>();

}
