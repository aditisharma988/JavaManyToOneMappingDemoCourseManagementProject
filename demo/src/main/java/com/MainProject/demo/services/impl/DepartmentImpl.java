package com.MainProject.demo.services.impl;

import com.MainProject.demo.models.entity.Course;
import com.MainProject.demo.models.entity.Department;
import com.MainProject.demo.models.entity.Subject;
import com.MainProject.demo.models.request.CourseRequestDto;
import com.MainProject.demo.models.request.DepartmentRequestDto;
import com.MainProject.demo.models.request.SubjectRequestDto;
import com.MainProject.demo.models.response.CourseResponseDto;
import com.MainProject.demo.models.response.DepartmentResponseDto;
import com.MainProject.demo.models.response.SubjectResponseDto;
import com.MainProject.demo.repository.CourseRepository;
import com.MainProject.demo.repository.DepartmentRepository;
import com.MainProject.demo.repository.SubjectRepository;
import com.MainProject.demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service

public class DepartmentImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CourseRepository courseRepository;

    //get all
    @Override
    public List<DepartmentResponseDto> getAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    // Get
    @Override
    public DepartmentResponseDto get(Long Id) {
        return departmentRepository.findById(Id)
                .map(this::convertToResponseDto)
                .orElse(new DepartmentResponseDto());
    }


    // create
    @Override
    public DepartmentResponseDto create(DepartmentRequestDto departmentRequestDto) {
        Department department = convertToEntity(departmentRequestDto);

        // for many to one mapping
        for (Course course : department.getCourses()) {
            course.setDepartment(department);

        }

        departmentRepository.save(department);
        return convertToResponseDto(department);

    }

    // Delete a department
    @Override
    public DepartmentResponseDto delete(Long id) {
        if (departmentRepository.existsById(id)) {
            Department department = departmentRepository.findById(id).orElse(null);
            departmentRepository.deleteById(id);
            return convertToResponseDto(department);
        }
        return null;
    }


    @Override
    public DepartmentResponseDto createAndUpdate(Long id, DepartmentRequestDto departmentRequestDto) {

        Department savedDepartment = departmentRepository.findById(id) .orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));

        updateDepartmentDetails(savedDepartment, departmentRequestDto);
        saveAndUpdateCourses(savedDepartment, departmentRequestDto);
        Department updatedDepartment = departmentRepository.save(savedDepartment);

        return convertToResponseDto(updatedDepartment);

    }

    private void updateDepartmentDetails(Department savedDepartment, DepartmentRequestDto departmentRequestDto) {

        savedDepartment.setName(departmentRequestDto.getName());
        savedDepartment.setDeptFee(departmentRequestDto.getDeptFee());
        savedDepartment.setDeptHead(departmentRequestDto.getDeptHead());
        savedDepartment.setDeptTopper(departmentRequestDto.getDeptTopper());

    }



    private void saveAndUpdateCourses(Department savedDepartment, DepartmentRequestDto departmentRequestDto) {
        List<Course> existingCourses = savedDepartment.getCourses();
        List<Course> updatedCourses = new ArrayList<>();

        for (CourseRequestDto courseRequestDto : departmentRequestDto.getCourses()) {
            Course course;

            if (courseRequestDto.getId() != null) {
                course = null;
                for (Course existingCourse : existingCourses) {
                    if (existingCourse.getId().equals(courseRequestDto.getId())) {
                        course = existingCourse;
                        break;
                    }
                }
                if (course == null) {
                    throw new IllegalArgumentException(
                            "Course with ID " + courseRequestDto.getId() + " not found");
                }
            } else {

                course = new Course();
                course.setDepartment(savedDepartment);
            }

            course.setName(courseRequestDto.getName());
            course.setCourseMentor(courseRequestDto.getCourseMentor());
            course.setPrice(courseRequestDto.getPrice());
            course.setDuration(courseRequestDto.getDuration());
            course.setType(courseRequestDto.getType());

            updatedCourses.add(course);
        }

       //courses to be deleted
        List<Long> courseRequestIds = new ArrayList<>();
        for (CourseRequestDto courseRequestDto : departmentRequestDto.getCourses()) {
            if (courseRequestDto.getId() != null) {
                courseRequestIds.add(courseRequestDto.getId());
            }
        }

        List<Course> coursesToDelete = new ArrayList<>();
        for (Course existingCourse : existingCourses) {
            if (!courseRequestIds.contains(existingCourse.getId())) {
                coursesToDelete.add(existingCourse);
            }
        }

        for (Course course : coursesToDelete) {
            savedDepartment.getCourses().remove(course);
            courseRepository.delete(course);
        }

        savedDepartment.getCourses().addAll(updatedCourses);

        departmentRepository.save(savedDepartment);
    }

    // Method to convert Department entity to DepartmentResponseDto
    public DepartmentResponseDto convertToResponseDto(Department department) {
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();

        departmentResponseDto.setId(department.getId());
        departmentResponseDto.setName(department.getName());
        departmentResponseDto.setDeptHead(department.getDeptHead());
        departmentResponseDto.setDeptFee(department.getDeptFee());

        List<CourseResponseDto> courseResponseDtos = new ArrayList<>();

        for (Course course : department.getCourses()) {
            CourseResponseDto courseResponseDto = new CourseResponseDto();

            courseResponseDto.setId(course.getId());
            courseResponseDto.setName(course.getName());
            courseResponseDto.setType(course.getType());
            courseResponseDto.setDuration(course.getDuration());
            courseResponseDto.setPrice(course.getPrice());
            courseResponseDtos.add(courseResponseDto);
        }

        departmentResponseDto.setCourses(courseResponseDtos);

        return departmentResponseDto;
    }


    public Department convertToEntity(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();

        if (departmentRequestDto != null) {
            department.setName(departmentRequestDto.getName());
            department.setDeptHead(departmentRequestDto.getDeptHead());
            department.setDeptTopper(departmentRequestDto.getDeptTopper());
            department.setDeptFee(departmentRequestDto.getDeptFee());

            List<Course> courses = new ArrayList<>();

            if (departmentRequestDto.getCourses() != null) {
                for (CourseRequestDto courseRequestDto : departmentRequestDto.getCourses()) {
                    Course course = new Course();


                    if (courseRequestDto != null) {
                        course.setName(courseRequestDto.getName());
                        course.setType(courseRequestDto.getType());
                        course.setDuration(courseRequestDto.getDuration());
                        course.setCourseMentor(courseRequestDto.getCourseMentor());
                        course.setPrice(courseRequestDto.getPrice());

                        course.setDepartment(department);
                        courses.add(course);
                    }
                }
            }

            department.setCourses(courses);
        }

        return department;
    }
}

