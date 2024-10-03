package com.easehire.application.service;

import com.easehire.application.dto.TpoDTO;

import java.util.List;

public interface TpoService {
    boolean create(TpoDTO tpoDTO);
    TpoDTO read(Long tpoId);
    List<TpoDTO> readAll();
    void delete(Long tpoId);
    TpoDTO deleteAtIndex(int index);
    TpoDTO update(TpoDTO tpoDTO, int index);
    void clear();
    void setTpoDTOS(List<TpoDTO> tpoDTOS);
}
