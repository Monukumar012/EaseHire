package com.easehire.application.repository;

import com.easehire.application.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long> {
    College findByCollegeCode(String collegeCode);
}
