package com.MainProject.demo.models.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentResponseDto {

    private Long id;
    private String name;
    private String deptHead;
    private int deptFee;


    private List<CourseResponseDto> courses;

}
