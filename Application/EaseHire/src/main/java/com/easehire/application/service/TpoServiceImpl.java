package com.easehire.application.service;

import com.easehire.application.dto.TpoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class TpoServiceImpl implements TpoService {
    private List<TpoDTO> tpoDTOS=new ArrayList<>();
    @Override
    public boolean create(TpoDTO tpoDTO) {
        return tpoDTOS.add(tpoDTO);
    }

    @Override
    public TpoDTO read(Long tpoId) {
        return tpoDTOS.stream().filter(tpoDTO -> Objects.equals(tpoDTO.getTpoId(), tpoId))
                .findFirst().orElse(null);
    }

    @Override
    public List<TpoDTO> readAll() {
        return Collections.unmodifiableList(tpoDTOS);
    }

    @Override
    public void delete(Long tpoId) {
        tpoDTOS.remove(read(tpoId));
    }

    @Override
    public TpoDTO deleteAtIndex(int index) {
        return tpoDTOS.remove(index);
    }

    @Override
    public TpoDTO update(TpoDTO tpoDTO, int index) {
        if(tpoDTO==null)return null;
        TpoDTO tpoDTO1=tpoDTOS.get(index);

        tpoDTO1.setName(tpoDTO.getName());
        tpoDTO1.setEmail(tpoDTO.getEmail());
        tpoDTO1.setYear(tpoDTO.getYear());
        tpoDTO1.setPhoneNumber(tpoDTO.getPhoneNumber());

        return tpoDTO1;
    }

    @Override
    public void clear() {
        tpoDTOS.clear();
    }

    public void setTpoDTOS(List<TpoDTO> tpoDTOS) {
        this.tpoDTOS = tpoDTOS;
    }
}
