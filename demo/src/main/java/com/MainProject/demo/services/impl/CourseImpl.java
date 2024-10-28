package com.MainProject.demo.services.impl;


import com.MainProject.demo.models.entity.Course;
import com.MainProject.demo.models.entity.Department;
import com.MainProject.demo.models.request.CourseRequestDto;
import com.MainProject.demo.models.request.DepartmentRequestDto;
import com.MainProject.demo.models.response.CourseResponseDto;
import com.MainProject.demo.models.response.DepartmentResponseDto;
import com.MainProject.demo.repository.CourseRepository;
import com.MainProject.demo.services.CourseService;
import com.MainProject.demo.services.mappers.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseImpl  implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<CourseResponseDto>getAllCourses(){
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseMapper::EntityToResponseDto)
                .toList();

    }


    @Override
    public List<Course> getCoursesByDepartmentId(Long id){

        //jpa query
       // return courseRepository.findByDepartment_Id(id);

        //native query
//        return courseRepository.getByDept_Id(id);

       //jpql query
        return courseRepository.getByDepart_Id(id);
    }
}





