package com.classhub.api.model.users;

import com.classhub.api.model.subjects.TeachingSubject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Teachers")
@RequiredArgsConstructor
public class Teacher  {
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
    public Teacher(Long id) {
        this.id= id;
    }

    @ManyToMany(mappedBy = "teachers")
    @JsonIgnore
    private Set<TeachingSubject> voting = new LinkedHashSet<>();
}
