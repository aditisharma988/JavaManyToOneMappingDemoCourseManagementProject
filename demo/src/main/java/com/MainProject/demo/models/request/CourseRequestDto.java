package com.MainProject.demo.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {

    private Long id;
    private String name;
    private String type;
    private int duration;
    private String courseMentor;
    private int price;

    private List<SubjectRequestDto> subjects = new ArrayList<>();
}

