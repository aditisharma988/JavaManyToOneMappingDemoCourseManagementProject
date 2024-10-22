package com.MainProject.demo.services;


import com.MainProject.demo.models.request.DepartmentRequestDto;
import com.MainProject.demo.models.response.DepartmentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    DepartmentResponseDto create(DepartmentRequestDto departmentRequestDto);

    List<DepartmentResponseDto> getAll();

    DepartmentResponseDto get( Long id);

    DepartmentResponseDto createAndUpdate(Long id, DepartmentRequestDto departmentRequestDto);

    DepartmentResponseDto delete(Long id);

}












//    List<DepartmentResponseDto> updateAll( List<DepartmentRequestDto> departmentRequestDtos);