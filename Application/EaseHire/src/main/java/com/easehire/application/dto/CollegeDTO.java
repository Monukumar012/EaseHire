package com.easehire.application.dto;


import com.easehire.application.entity.College;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollegeDTO {
    private Long collegeId;
    private String collegeCode;
    private String collegeName;
    private String collegeShortName;
    private String city;
    private String affiliation;
    private List<TpoDTO> tpos=new ArrayList<>();
    private List<CoordinatorDTO> coordinators=new ArrayList<>();

    public CollegeDTO(Long collegeId) {
        this.collegeId = collegeId;
    }

    public final College toEntity(){
        return College.builder()
                .collegeId(collegeId)
                .collegeCode(collegeCode)
                .collegeName(collegeName)
                .collegeShortName(collegeShortName)
                .city(city)
                .affiliation(affiliation)
                .tpos(tpos.stream().map(TpoDTO::toEntity).collect(Collectors.toList()))
                .coordinators(coordinators.stream().map(CoordinatorDTO::toEntity).collect(Collectors.toList()))
                .build();
    }
}
