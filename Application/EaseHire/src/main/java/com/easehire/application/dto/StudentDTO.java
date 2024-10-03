package com.easehire.application.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.easehire.application.entity.Student;
import com.easehire.application.utility.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Long studentId;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;
    private String email;
    private Gender gender;
    private Long connectId;
    private Long contactNumber;

    private EducationInfoDTO educationInfo=new EducationInfoDTO();
    private AssessmentInfoDTO assessmentInfo=new AssessmentInfoDTO();
    private InterviewInfoDTO interviewInfo=new InterviewInfoDTO();


    public final Student toEntity(){
        return Student.builder()
                .studentId(studentId)
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .email(email)
                .gender(gender)
                .connectId(connectId)
                .contactNumber(contactNumber)
                .educationInfo(educationInfo==null ? null : educationInfo.toEntity())
                .assessmentInfo(assessmentInfo==null ? null : assessmentInfo.toEntity())
                .interviewInfo(interviewInfo==null ? null : interviewInfo.toEntity())
                .build();
    }
}
