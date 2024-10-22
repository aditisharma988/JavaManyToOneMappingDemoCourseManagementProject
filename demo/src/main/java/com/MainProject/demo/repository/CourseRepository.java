package com.MainProject.demo.repository;

import com.MainProject.demo.models.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {

}
