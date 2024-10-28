package com.MainProject.demo.services;


import com.MainProject.demo.models.entity.Course;
import com.MainProject.demo.models.response.CourseResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {



    public List<CourseResponseDto> getAllCourses();

//    public  List<CourseResponseDto> getCoursesByDepartmentId(Long id);
    public  List<Course> getCoursesByDepartmentId(Long id);

}
