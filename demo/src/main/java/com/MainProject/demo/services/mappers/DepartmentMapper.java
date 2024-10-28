package com.MainProject.demo.services.mappers;

import com.MainProject.demo.models.entity.Department;
import com.MainProject.demo.models.request.DepartmentRequestDto;
import com.MainProject.demo.models.response.DepartmentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

    DepartmentResponseDto EntityToResponseDto(Department department);

//    @Mapping(target = "courses", ignore = true)
    Department RequestToEntity(DepartmentRequestDto departmentRequestDto);
    
    @Mapping(target = "courses", ignore = true)
    void updateDepartmentFromDto(@MappingTarget Department department, DepartmentRequestDto departmentRequestDto);

}
