package com.easehire.application.entity;


import com.easehire.application.dto.AssessmentInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "EASE_HIRE_ASSESSMENT_INFO")
@DynamicInsert
@DynamicUpdate
@TableGenerator(name="EASE_HIRE_ID_GENERATOR",pkColumnValue = "EASE_HIRE_ASSESSMENT_INFO",initialValue=100000, allocationSize=1)
public class AssessmentInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="EASE_HIRE_ID_GENERATOR")
    private Long assessmentInfoId;
    private Double cognitiveAbilityMarks;
    private Double codingMarks;
    private Double technicalMarks;
    private Double totalMarks;
    private Double finalMarks; // some % of totalMarks, and apply cut off on finalMarks
    private String result;
    private String reportLink;
    private LocalDate assessmentTime;

    public final AssessmentInfoDTO toDTO(){
        return AssessmentInfoDTO.builder()
                .assessmentInfoId(assessmentInfoId)
                .cognitiveAbilityMarks(cognitiveAbilityMarks)
                .codingMarks(codingMarks)
                .technicalMarks(technicalMarks)
                .totalMarks(totalMarks)
                .finalMarks(finalMarks)
                .result(result)
                .reportLink(reportLink)
                .assessmentTime(assessmentTime)
                .build();
    }
}
