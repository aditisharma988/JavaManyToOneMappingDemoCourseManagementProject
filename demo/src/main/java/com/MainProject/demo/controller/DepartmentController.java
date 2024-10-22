package com.MainProject.demo.controller;

import com.MainProject.demo.exception.EntityAlreadyExistsException;
import com.MainProject.demo.exception.EntityNotFoundException;
import com.MainProject.demo.models.request.DepartmentRequestDto;
import com.MainProject.demo.models.response.DepartmentResponseDto;
import com.MainProject.demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Get a specific department
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentDetailsById(@PathVariable("id") Long id) {
        DepartmentResponseDto departmentResponseDto = departmentService.get(id);

        if (departmentResponseDto == null) {
            throw new EntityNotFoundException("Department with ID " + id + " not found.");
        }

        return ResponseEntity.ok(departmentResponseDto);
    }


    // Get all departments
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        List<DepartmentResponseDto> departments = departmentService.getAll();
        return ResponseEntity.ok(departments);
    }

    // Create a new department
    @PostMapping("/create")
    public ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
        try {
            DepartmentResponseDto createdDept = departmentService.create(departmentRequestDto);
            return ResponseEntity.status(201).body(createdDept);
        }
        catch(EntityAlreadyExistsException e) {
            throw  new EntityAlreadyExistsException("Department already exists");

        }
    }

    // Update a specific department
    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartmentById(
            @PathVariable("id") Long id,
            @RequestBody DepartmentRequestDto departmentRequestDto) {
        DepartmentResponseDto updatedDepartment = departmentService.createAndUpdate(id, departmentRequestDto);
        return ResponseEntity.status(200).body(updatedDepartment);
    }


    // Delete a specific department
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") Long id) {

        try{
            departmentService.delete(id);
            return ResponseEntity.status(204).build();
        }
    catch(EntityNotFoundException e){
        throw new EntityNotFoundException("Department with ID " + id + " not found.");
    }
}
}
