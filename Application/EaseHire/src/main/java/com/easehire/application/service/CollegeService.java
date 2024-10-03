package com.easehire.application.service;

import com.easehire.application.dto.CollegeDTO;

import java.util.List;

public interface CollegeService {
    CollegeDTO create(CollegeDTO collegeDTO);
    CollegeDTO read(Long collegeId);
    List<CollegeDTO> readAll();
    void delete(Long collegeId);

    CollegeDTO update(CollegeDTO collegeDTO);

    CollegeDTO readByCollegeCode(String collegeCode);

    long countAllColleges();
}
