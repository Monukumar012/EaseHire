package com.easehire.application.entity;


import com.easehire.application.dto.EducationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "EASE_HIRE_EDUCATION_INFO")
@DynamicInsert
@DynamicUpdate
@TableGenerator(name="EASE_HIRE_ID_GENERATOR",pkColumnValue = "EASE_HIRE_EDUCATION_INFO",initialValue=100000, allocationSize=1)
public class EducationInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="EASE_HIRE_ID_GENERATOR")
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

    @ManyToOne
    @JoinColumn(name = "College_ID")
    private College college;

    public final EducationInfoDTO toDTO(){
        return EducationInfoDTO.builder()
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
                .collegeDTO(college==null ? null : college.toDTO())
                .build();
    }
}
