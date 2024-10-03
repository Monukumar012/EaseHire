package com.easehire.application.dto;


import com.easehire.application.entity.EducationInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationInfoDTO {
    private Long educationInfoId;
    private Double highSchoolPercentage;
    private Double intermediatePercentage;
    private String graduation;
    private Double graduationPercentage;
    private String branch;
    private Integer graduationYear;
    private String postGraduation;
    private Double postGraduationPercentage;
    private Integer postGraduationYear;
    private CollegeDTO collegeDTO=new CollegeDTO();


    public final EducationInfo toEntity(){
        return EducationInfo.builder()
                .educationInfoId(educationInfoId)
                .highSchoolPercentage(highSchoolPercentage)
                .intermediatePercentage(intermediatePercentage)
                .graduation(graduation)
                .graduationPercentage(graduationPercentage)
                .branch(branch)
                .graduationYear(graduationYear)
                .postGraduation(postGraduation)
                .postGraduationPercentage(postGraduationPercentage)
                .postGraduationYear(postGraduationYear)
                .college(collegeDTO==null ? null : collegeDTO.toEntity())
                .build();
    }
}
