package com.easehire.application.entity;


import com.easehire.application.dto.StudentDTO;
import com.easehire.application.utility.enums.Gender;
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
@Table(name = "EASE_HIRE_STUDENT")
@DynamicInsert
@DynamicUpdate
@TableGenerator(name="EASE_HIRE_ID_GENERATOR",pkColumnValue = "EASE_HIRE_STUDENT",initialValue=100000, allocationSize=1)
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="EASE_HIRE_ID_GENERATOR")
    private Long studentId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Long contactNumber;
    private Long connectId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "educationInfo_ID")
    private EducationInfo educationInfo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assessmentInfo_ID")
    private AssessmentInfo assessmentInfo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "interviewInfo_ID")
    private InterviewInfo interviewInfo;

    public final StudentDTO toDTO(){
        return StudentDTO.builder()
                .studentId(studentId)
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .email(email)
                .gender(gender)
                .contactNumber(contactNumber)
                .connectId(connectId)
                .educationInfo(educationInfo==null ? null: educationInfo.toDTO())
                .assessmentInfo(assessmentInfo==null ? null : assessmentInfo.toDTO())
                .interviewInfo(interviewInfo==null ? null : interviewInfo.toDTO())
                .build();
    }
}
