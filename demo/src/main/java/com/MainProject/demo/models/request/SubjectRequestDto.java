package com.MainProject.demo.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequestDto {

    private Long id;
    private String name;
    private String subjTeacher;
    private String subjTopper;

}
