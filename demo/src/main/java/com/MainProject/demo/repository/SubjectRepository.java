package com.MainProject.demo.repository;

import com.MainProject.demo.models.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
