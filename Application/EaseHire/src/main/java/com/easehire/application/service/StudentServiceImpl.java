package com.easehire.application.service;

import com.easehire.application.dto.*;
import com.easehire.application.entity.Student;
import com.easehire.application.exception.InternalServerException;
import com.easehire.application.exception.InvalidDataFormatException;
import com.easehire.application.exception.NotFoundException;
import com.easehire.application.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.UnsupportedFileFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.easehire.application.utility.Utility.*;

@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CollegeService collegeService;
    private final ConnectService connectService;

    @Override
    public Boolean create(StudentDTO studentDTO) {
        if(studentDTO==null)return false;
        studentRepository.save(studentDTO.toEntity());
        return true;
    }

    @Override
    public StudentDTO read(Long studentId) {
        return studentRepository.findById(studentId).map(Student::toDTO)
                .orElseThrow(()->new NotFoundException(String.format("Student is Not Exists for given Student ID: %d", studentId)));
    }

    @Override
    public List<StudentDTO> readAll() {
        return studentRepository.findAll().stream().map(Student::toDTO).collect(Collectors.toList());
    }

    @Override
    public Long uploadStudent(MultipartFile file, Long connectId, String collegeCode) throws UnsupportedFileFormatException{
        List<StudentDTO> studentDTOS=new ArrayList<>();
        try(InputStream in=file.getInputStream()){
            Sheet sheet=verifyFileFormat(file.getOriginalFilename(), in);

            if(sheet==null){
                log.warn("File Format Not Supported!");
                throw new UnsupportedFileFormatException("File Format Not Supported") {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                };
            }
            if(!validateHeaders(sheet.getRow(0))){
                log.warn("Given Excel Sheet Header Not Matched!");
                throw new InvalidDataFormatException("Invalid Sheet Headers");
            }

            for(Row row: sheet){
                if(row==sheet.getRow(0))continue;
                String studentCollegeCode = row.getCell(19).getStringCellValue();

                // if student college id not equal to current college id then skip this student
                if(!Objects.equals(studentCollegeCode, collegeCode)){
                    log.debug("Student Not Exist from current College: Excepted College Code = {}, Actual College Code = {}",collegeCode, studentCollegeCode);
                    continue;
                }

                StudentDTO studentDTO=buildStudentDTOFromRow(row, connectId, collegeCode);

                log.debug("Student Build:  {}", studentDTO);
                studentDTOS.add(studentDTO);
            }
        } catch (IOException e) {
            log.error("Error while reading file: {}",e.getMessage());
            throw new InternalServerException("Unable to Read the File");
        }
        long cnt= create(studentDTOS);

        log.info("{} students uploaded from file for connect Id = {}",cnt, connectId);
        if(cnt>0)connectService.setStatusToInactive(connectId); // update connect to inactive
        return cnt;
    }

    private StudentDTO buildStudentDTOFromRow(Row row, Long connectId, String collegeCode) {

        CollegeDTO collegeDTO = collegeService.readByCollegeCode(collegeCode);

        EducationInfoDTO educationInfoDTO = new EducationInfoDTO();
        educationInfoDTO.setCollegeDTO(collegeDTO);

        AssessmentInfoDTO assessmentInfoDTO = new AssessmentInfoDTO();
        InterviewInfoDTO interviewInfoDTO = new InterviewInfoDTO();

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setFirstName(parseCell(row.getCell(0)));
        studentDTO.setLastName(parseCell(row.getCell(1)));
        studentDTO.setGender(parseGender(row.getCell(2)));
        studentDTO.setEmail(parseCell(row.getCell(3)));
        studentDTO.setDateOfBirth(parseDate(row.getCell(11)));
        studentDTO.setContactNumber(parseLong(row.getCell(12)));

        assessmentInfoDTO.setTotalMarks(parseDouble(row.getCell(4)));
        assessmentInfoDTO.setCognitiveAbilityMarks(parseDouble(row.getCell(5)));
        assessmentInfoDTO.setCodingMarks(parseDouble(row.getCell(6)));
        assessmentInfoDTO.setTechnicalMarks(parseDouble(row.getCell(7)));
        assessmentInfoDTO.setFinalMarks(parseDouble(row.getCell(8)));
        assessmentInfoDTO.setResult(row.getCell(9).getStringCellValue());
        assessmentInfoDTO.setReportLink(parseCell(row.getCell(10)));

        assessmentInfoDTO.setReportLink(parseCell(row.getCell(10)));

        educationInfoDTO.setHighSchoolPercentage(parseDouble(row.getCell(13)));
        educationInfoDTO.setIntermediatePercentage(parseDouble(row.getCell(14)));
        educationInfoDTO.setGraduation(parseCell(row.getCell(15)));
        educationInfoDTO.setGraduationPercentage(parseDouble(row.getCell(16)));
        educationInfoDTO.setBranch(parseCell(row.getCell(17)));
        educationInfoDTO.setGraduationYear(parseInteger(row.getCell(18)));

        educationInfoDTO.setPostGraduation(parseString(row.getCell(20)));
        educationInfoDTO.setPostGraduationPercentage(parseDouble(row.getCell(21)));
        educationInfoDTO.setPostGraduationYear(parseInteger(row.getCell(22)));

        studentDTO.setEducationInfo(educationInfoDTO);
        studentDTO.setAssessmentInfo(assessmentInfoDTO);
        studentDTO.setInterviewInfo(interviewInfoDTO);
        studentDTO.setConnectId(connectId);

        return studentDTO;
    }

    @Override
    public Long create(List<StudentDTO> studentDTOS) {
        log.info("create(List<StudentDTO> studentDTOS) : {} ",studentDTOS);
        return  (long) studentRepository.saveAll(studentDTOS.stream().map(StudentDTO::toEntity).collect(Collectors.toList())).size();
    }

    @Override
    public long countAllStudents() {
        return studentRepository.count();
    }
}
