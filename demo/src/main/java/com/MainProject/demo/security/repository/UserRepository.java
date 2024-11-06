package com.MainProject.demo.security.repository;

import com.MainProject.demo.security.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

   Optional<Users> findByEmail(String email);
}
