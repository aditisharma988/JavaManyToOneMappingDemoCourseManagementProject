package com.MainProject.demo.models.response;

import com.MainProject.demo.models.dto.DepartmentDto;
import com.MainProject.demo.models.entity.Department;
import lombok.Data;

import java.util.List;

@Data
public class CourseResponseDto {

    private Long id;
    private String name;
    private String type;
    private int duration;
    private int price;
    private String courseMentor;
//    private DepartmentDto department;

//    private List<SubjectResponseDto> subjects;

}

