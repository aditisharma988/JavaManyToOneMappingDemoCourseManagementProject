package com.MainProject.demo.models.response;

import lombok.Data;

import java.util.List;

@Data
public class CourseResponseDto {

    private Long id;
    private String name;
    private String type;
    private int duration;
    private int price;

    private List<SubjectResponseDto> subjects;

}

