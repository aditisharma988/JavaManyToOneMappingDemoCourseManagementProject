package com.MainProject.demo.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDto {

    private Long id;
    private String name;
    private String deptHead;
    private int deptFee;
    private String deptTopper;

    private List<CourseRequestDto> courses;

}