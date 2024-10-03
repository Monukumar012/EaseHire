package com.easehire.application.service;

import com.easehire.application.dto.ConnectDTO;
import com.easehire.application.utility.enums.Status;

import java.util.List;

public interface ConnectService {
    Boolean create(ConnectDTO connectDTO);
    ConnectDTO read(Long connectId);
    List<ConnectDTO> readAll();

    ConnectDTO update(ConnectDTO connectDTO) throws UnsupportedOperationException;

    void delete(Long connectId, Status status) throws UnsupportedOperationException;

    void setStatusToInactive(Long connectId);

    List<ConnectDTO> readActiveConnects();

    long countAllConnects();
}
