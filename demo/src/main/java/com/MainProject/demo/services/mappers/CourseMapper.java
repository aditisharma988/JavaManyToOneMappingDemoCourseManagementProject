package com.MainProject.demo.services.mappers;


import com.MainProject.demo.models.entity.Course;
import com.MainProject.demo.models.entity.Department;
import com.MainProject.demo.models.request.CourseRequestDto;
import com.MainProject.demo.models.response.CourseResponseDto;
import com.MainProject.demo.models.response.DepartmentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {


    CourseResponseDto EntityToResponseDto (Course course);

//    Course RequestToEntity(CourseRequestDto courseRequestDto);

//    void updateCourseFromDto(@MappingTarget Course course, CourseRequestDto courseRequestDto);


}
