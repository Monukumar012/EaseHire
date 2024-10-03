package com.easehire.application.dto;


import com.easehire.application.entity.InterviewInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewInfoDTO {
    private Long interviewInfoId;
    private LocalDate interviewDate;
    private String interviewer;
    private String result;
    private String comments;

    public final InterviewInfo toEntity(){
        return InterviewInfo.builder()
                .interviewInfoId(interviewInfoId)
                .interviewDate(interviewDate)
                .interviewer(interviewer)
                .result(result)
                .comments(comments)
                .build();
    }
}
