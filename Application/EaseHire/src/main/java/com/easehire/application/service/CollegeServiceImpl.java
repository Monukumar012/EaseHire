package com.easehire.application.service;

import com.easehire.application.dto.CollegeDTO;
import com.easehire.application.entity.College;
import com.easehire.application.exception.NotFoundException;
import com.easehire.application.repository.CollegeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CollegeServiceImpl implements CollegeService {

    private final CollegeRepository collegeRepository;

    @Override
    public CollegeDTO create(CollegeDTO collegeDTO) {
        if(collegeDTO==null)return null;
        return collegeRepository.save(collegeDTO.toEntity()).toDTO();
    }

    @Override
    public CollegeDTO read(Long collegeId) {
        return collegeRepository.findById(collegeId).map(College::toDTO)
                .orElseThrow(()->new NotFoundException(String.format("College is Not Exists for given College ID: %d",collegeId)));
    }

    @Override
    public List<CollegeDTO> readAll() {
        return collegeRepository.findAll().stream().map(College::toDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Long collegeId) {
        collegeRepository.deleteById(collegeId);

    }

    @Override
    public CollegeDTO update(CollegeDTO collegeDTO) {
        if(collegeDTO==null)return null;
        return collegeRepository.save(collegeDTO.toEntity()).toDTO();
    }

    @Override
    public CollegeDTO readByCollegeCode(String collegeCode) {
        return collegeRepository.findByCollegeCode(collegeCode).toDTO();
    }

    @Override
    public long countAllColleges() {
        return collegeRepository.count();
    }
}
