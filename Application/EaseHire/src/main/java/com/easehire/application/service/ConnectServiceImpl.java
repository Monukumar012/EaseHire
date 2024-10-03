package com.easehire.application.service;

import com.easehire.application.dto.ConnectDTO;
import com.easehire.application.entity.Connect;
import com.easehire.application.exception.NotFoundException;
import com.easehire.application.repository.ConnectRepository;
import com.easehire.application.utility.enums.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConnectServiceImpl implements ConnectService {

    private final ConnectRepository connectRepository;

    @Override
    public Boolean create(ConnectDTO connectDTO) {
        if(connectDTO==null)return false;
        return connectRepository.save(connectDTO.toEntity())!=null;
    }

    @Override
    public ConnectDTO read(Long connectId) {
        return connectRepository.findById(connectId).map(Connect::toDTO)
                .orElseThrow(()->new NotFoundException(String.format("Connect is Not Exists for given Connect ID: %d", connectId)));
    }

    @Override
    public List<ConnectDTO> readAll() {
        return connectRepository.findAll().stream().map(Connect::toDTO).collect(Collectors.toList());
    }

    @Override
    public ConnectDTO update(ConnectDTO connectDTO) throws UnsupportedOperationException {
        if(connectDTO==null)return null;
        if(connectDTO.getStatus()==Status.INACTIVE){
            throw new UnsupportedOperationException("You cannot edit this because it is inactive.");
        }

        return connectRepository.save(connectDTO.toEntity()).toDTO();
    }

    @Override
    public void delete(Long connectId, Status status) throws UnsupportedOperationException{
        if(status==Status.INACTIVE){
            throw new UnsupportedOperationException("You cannot delete this because it is inactive");
        }
        connectRepository.deleteById(connectId);
    }

    @Override
    public void setStatusToInactive(Long connectId) {
        connectRepository.updateStatusByConnectId(connectId, Status.INACTIVE);
    }

    @Override
    public List<ConnectDTO> readActiveConnects() {
        return connectRepository.findAll().stream()
                .filter(connect -> Status.ACTIVE==connect.getStatus())
                .map(Connect::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public long countAllConnects() {
        return connectRepository.count();
    }
}
