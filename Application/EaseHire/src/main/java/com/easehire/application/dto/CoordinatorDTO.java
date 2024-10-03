package com.easehire.application.dto;


import com.easehire.application.entity.Coordinator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinatorDTO {
    private Long  coordinatorId;
    private String name;
    private String email;
    private Long phoneNumber;
    private Integer year;

    public final Coordinator toEntity(){
        return Coordinator.builder()
                .coordinatorId(coordinatorId)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .year(year)
                .build();
    }
}
