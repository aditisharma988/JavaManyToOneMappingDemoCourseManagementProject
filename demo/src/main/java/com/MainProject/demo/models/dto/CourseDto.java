package com.MainProject.demo.models.dto;

import lombok.Data;

import java.util.List;


@Data
public  class CourseDto {

    private Long id;
    private String name;
    private String type;
    private int duration;
    private int price;

    private List<SubjectDto> subjects ;

}
