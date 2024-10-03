package com.easehire.application.entity;


import com.easehire.application.dto.TpoDTO;
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
@Table(name = "EASE_HIRE_TPO")
@DynamicInsert
@DynamicUpdate
@TableGenerator(name="EASE_HIRE_ID_GENERATOR",pkColumnValue = "EASE_HIRE_TPO",initialValue=100000, allocationSize=1)
public class Tpo {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="EASE_HIRE_ID_GENERATOR")
    private Long tpoId;
    private String name;
    private String email;
    private Long phoneNumber;
    private Integer year;

    public final TpoDTO toDTO(){
        return TpoDTO.builder()
                .tpoId(tpoId)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .year(year)
                .build();
    }
}
