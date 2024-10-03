package com.easehire.application.entity;


import com.easehire.application.dto.ConnectDTO;
import com.easehire.application.utility.enums.Status;
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
@Table(name = "EASE_HIRE_CONNECT")
@DynamicInsert
@DynamicUpdate
@TableGenerator(name="EASE_HIRE_ID_GENERATOR",pkColumnValue = "EASE_HIRE_CONNECT",initialValue=100000, allocationSize=1)
public class Connect {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="EASE_HIRE_ID_GENERATOR")
    private Long connectId;
    private LocalDate prePlacementTalkDate;
    private LocalDate assessmentDate;
    private String schedule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "College_ID")
    private College college;
    @Enumerated(EnumType.STRING)
    private Status status;

    @PrePersist
    protected void onCreate(){
        if(status==null)
            status=Status.ACTIVE;
    }

    public final ConnectDTO toDTO(){
        return ConnectDTO.builder()
                .connectId(connectId)
                .prePlacementTalkDate(prePlacementTalkDate)
                .assessmentDate(assessmentDate)
                .schedule(schedule)
                .collegeDTO(college==null ? null : college.toDTO())
                .status(status)
                .build();
    }
}
