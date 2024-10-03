package com.easehire.application.entity;


import com.easehire.application.dto.CoordinatorDTO;
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
@Table(name = "EASE_HIRE_COORDINATOR")
@DynamicInsert
@DynamicUpdate
@TableGenerator(name="EASE_HIRE_ID_GENERATOR",pkColumnValue = "EASE_HIRE_COORDINATOR",initialValue=100000, allocationSize=1)
public class Coordinator {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="EASE_HIRE_ID_GENERATOR")
    private Long  coordinatorId;
    private String name;
    private String email;
    private Long phoneNumber;
    private Integer year;

    public final CoordinatorDTO toDTO(){
        return CoordinatorDTO.builder()
                .coordinatorId(coordinatorId)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .year(year)
                .build();
    }
}
