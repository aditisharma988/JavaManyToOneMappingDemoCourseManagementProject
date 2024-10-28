package com.MainProject.demo.controller;

import com.MainProject.demo.models.entity.Course;
import com.MainProject.demo.models.response.CourseResponseDto;
import com.MainProject.demo.services.CourseService;
import com.MainProject.demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses(){
        List<CourseResponseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

//    @GetMapping("/department/{id}")
//    public ResponseEntity<List<CourseResponseDto>> getCoursesByDepartmentId(@PathVariable("id") Long id){
//        List<CourseResponseDto> courses = courseService.getCoursesByDepartmentId(id);
//
//        return ResponseEntity.ok(courses);



       @GetMapping("/department/{id}")
        public ResponseEntity<List<Course>> getCoursesByDepartmentId(@PathVariable("id") Long id){

           List<Course> courses = courseService.getCoursesByDepartmentId(id);
            return ResponseEntity.ok(courses);


        }


}
