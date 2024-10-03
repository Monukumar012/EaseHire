package com.easehire.application.service;

import com.easehire.application.dto.CoordinatorDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class CoordinatorServiceImpl implements CoordinatorService{
    private List<CoordinatorDTO> coordinatorDTOS=new ArrayList<>();
    @Override
    public boolean create(CoordinatorDTO coordinatorDTO) {
        return coordinatorDTOS.add(coordinatorDTO);
    }

    @Override
    public CoordinatorDTO read(Long coordinatorId) {
        return coordinatorDTOS.stream().filter(coordinatorDTO -> Objects.equals(coordinatorDTO.getCoordinatorId(), coordinatorId))
                .findFirst().orElse(null);
    }

    @Override
    public List<CoordinatorDTO> readAll() {
        return Collections.unmodifiableList(coordinatorDTOS);
    }

    @Override
    public void delete(Long coordinatorId) {
        coordinatorDTOS.remove(read(coordinatorId));
    }

    @Override
    public CoordinatorDTO deleteAtIndex(int index) {
        return coordinatorDTOS.remove(index);
    }

    @Override
    public CoordinatorDTO update(CoordinatorDTO coordinatorDTO, int index) {
        if(coordinatorDTO==null)return null;
        CoordinatorDTO coordinatorDTO1=coordinatorDTOS.get(index);

        coordinatorDTO1.setName(coordinatorDTO.getName());
        coordinatorDTO1.setEmail(coordinatorDTO.getEmail());
        coordinatorDTO1.setYear(coordinatorDTO.getYear());
        coordinatorDTO1.setPhoneNumber(coordinatorDTO.getPhoneNumber());

        return coordinatorDTO1;
    }

    @Override
    public void clear() {
        coordinatorDTOS.clear();
    }

    public void setCoordinatorDTOS(List<CoordinatorDTO> coordinatorDTOS) {
        this.coordinatorDTOS = coordinatorDTOS;
    }
}
