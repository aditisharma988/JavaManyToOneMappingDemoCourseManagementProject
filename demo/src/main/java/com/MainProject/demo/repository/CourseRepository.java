package com.MainProject.demo.repository;

import com.MainProject.demo.models.entity.Course;
import com.MainProject.demo.models.response.CourseResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {


  // spring data derived jpa query
    // List<Course> findByDepartment_Id(Long id);


//native query
//    @Query(value ="SELECT * FROM courses WHERE dept_id =:id" , nativeQuery = true)
//    List<Course> findByDept_Id(@Param("id") Long id);



//jpql query
    @Query("SELECT courses FROM Course courses WHERE courses.department.id = :deptId")
    List<Course> getByDepart_Id(@Param("deptId") Long id);



}
