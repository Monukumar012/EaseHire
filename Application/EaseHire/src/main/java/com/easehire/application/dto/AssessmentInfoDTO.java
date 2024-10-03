package com.easehire.application.dto;


import com.easehire.application.entity.AssessmentInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentInfoDTO {
    private Long assessmentInfoId;
    private Double cognitiveAbilityMarks;
    private Double codingMarks;
    private Double technicalMarks;
    private Double totalMarks;
    private Double finalMarks; // some % of totalMarks, and apply cut off on finalMarks
    private String result;
    private String reportLink;
    private LocalDate assessmentTime;

    public final AssessmentInfo toEntity(){
        return AssessmentInfo.builder()
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
