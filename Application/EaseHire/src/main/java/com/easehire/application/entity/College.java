package com.easehire.application.entity;


import com.easehire.application.dto.CollegeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "EASE_HIRE_COLLEGE")
@DynamicInsert
@DynamicUpdate
@TableGenerator(name="EASE_HIRE_ID_GENERATOR",pkColumnValue = "EASE_HIRE_COLLEGE",initialValue=100000, allocationSize=1)
public class College {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="EASE_HIRE_ID_GENERATOR")
    private Long collegeId;
    @Column(unique = true)
    private String collegeCode;
    private String collegeName;
    private String collegeShortName;
    private String city;
    private String affiliation;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "collegeId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Tpo> tpos=Collections.emptyList();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "collegeId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Coordinator> coordinators= Collections.emptyList();

    public final CollegeDTO toDTO(){
        return CollegeDTO.builder()
                .collegeId(collegeId)
                .collegeCode(collegeCode)
                .collegeName(collegeName)
                .collegeShortName(collegeShortName)
                .city(city)
                .affiliation(affiliation)
                .tpos(tpos.stream().map(Tpo::toDTO).collect(Collectors.toList()))
                .coordinators(coordinators.stream().map(Coordinator::toDTO).collect(Collectors.toList()))
                .build();
    }
}
