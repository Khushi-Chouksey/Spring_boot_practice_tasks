package com.pac.repository;

import com.pac.model.student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<student, Long> {
}
