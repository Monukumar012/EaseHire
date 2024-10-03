package com.easehire.application.service;

import com.easehire.application.dto.StudentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    Boolean create(StudentDTO studentDTO);
    StudentDTO read(Long studentId);
    List<StudentDTO> readAll();
    Long uploadStudent(MultipartFile multipartFile, Long connectId, String collegeCode);
    Long create(List<StudentDTO> studentDTOS);

    long countAllStudents();
}
