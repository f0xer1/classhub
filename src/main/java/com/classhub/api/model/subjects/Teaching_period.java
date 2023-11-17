package com.classhub.api.model.subjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teaching_periods")
public class Teaching_period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "semester")
    private Integer semester;
}

