package com.easehire.application.entity;


import com.easehire.application.dto.InterviewInfoDTO;
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
@Table(name = "EASE_HIRE_INTERVIEW_INFO")
@DynamicInsert
@DynamicUpdate
@TableGenerator(name="EASE_HIRE_ID_GENERATOR", pkColumnValue = "EASE_HIRE_INTERVIEW_INFO",initialValue=100000, allocationSize=1)
public class InterviewInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="EASE_HIRE_ID_GENERATOR")
    private Long interviewInfoId;
    private LocalDate interviewDate;
    private String interviewer;
    private String result;
    private String comments;

    public final InterviewInfoDTO toDTO(){
        return InterviewInfoDTO.builder()
                .interviewInfoId(interviewInfoId)
                .interviewDate(interviewDate)
                .interviewer(interviewer)
                .result(result)
                .comments(comments)
                .build();
    }
}
