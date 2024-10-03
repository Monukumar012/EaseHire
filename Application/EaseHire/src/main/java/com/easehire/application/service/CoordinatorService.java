package com.easehire.application.service;

import com.easehire.application.dto.CoordinatorDTO;

import java.util.List;

public interface CoordinatorService {
    boolean create(CoordinatorDTO coordinatorDTO);
    CoordinatorDTO read(Long coordinatorId);
    List<CoordinatorDTO> readAll();
    void delete(Long coordinatorId);
    CoordinatorDTO deleteAtIndex(int index);
    CoordinatorDTO update(CoordinatorDTO coordinatorDTO, int index);
    void clear();
    void setCoordinatorDTOS(List<CoordinatorDTO> coordinatorDTOS);
}
