package com.easehire.application.dto;


import com.easehire.application.entity.Tpo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TpoDTO {
    private Long tpoId;
    private String name;
    private String email;
    private Long phoneNumber;
    private Integer year;

    public final Tpo toEntity(){
        return Tpo.builder()
                .tpoId(tpoId)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .year(year)
                .build();
    }
}
