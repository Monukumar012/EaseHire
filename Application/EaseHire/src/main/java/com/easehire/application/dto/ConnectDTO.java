package com.easehire.application.dto;


import com.easehire.application.entity.Connect;
import com.easehire.application.utility.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConnectDTO {
    private Long connectId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate prePlacementTalkDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate assessmentDate;
    private String schedule;
    private CollegeDTO collegeDTO=new CollegeDTO();
    private Status status=Status.ACTIVE;

    public ConnectDTO(Long connectId) {
        this.connectId = connectId;
    }

    public final Connect toEntity(){
        return Connect.builder()
                .connectId(connectId)
                .prePlacementTalkDate(prePlacementTalkDate)
                .assessmentDate(assessmentDate)
                .college(collegeDTO==null ? null : collegeDTO.toEntity())
                .status(status)
                .build();
    }
}
