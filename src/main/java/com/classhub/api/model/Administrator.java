package com.classhub.api.model;

import com.classhub.api.model.User;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrators")
@RequiredArgsConstructor
public class Administrator  {
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


    public Administrator(Long id) {
        this.id= id;
    }
}
