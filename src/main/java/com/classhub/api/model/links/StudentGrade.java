package com.classhub.api.model.links;

import com.classhub.api.model.subjects.Task;
import com.classhub.api.model.users.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student_grades", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"task_id", "student_id"})
})
public class StudentGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "grade")
    private int grade;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
