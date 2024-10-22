package com.MainProject.demo.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {

    private Long id;
    private String name;
    private String deptHead;
    private int deptFee;

    private List<CourseDto> courses;


}
