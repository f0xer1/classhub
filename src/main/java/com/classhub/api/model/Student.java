package com.classhub.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Students")

public class Student {
    @Id
    @JoinColumn(name = "id")
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "patronymic")
    private String patronymic;


    @Column(name = "cluster")
    private String cluster;

    @Column(name = "faculty")
    private String faculty;
}
