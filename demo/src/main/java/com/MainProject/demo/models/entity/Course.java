package com.MainProject.demo.models.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="courses")
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private int duration;

    private String courseMentor;

    private int price;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Subject> subjects = new ArrayList<>();

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="dept_id")
    private Department department;

}
