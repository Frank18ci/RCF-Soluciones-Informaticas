package com.rcf.usersservice.service.impl;

import com.rcf.usersservice.dto.StateRegionRequest;
import com.rcf.usersservice.dto.StateRegionResponse;
import com.rcf.usersservice.exception.ResourceNotFound;
import com.rcf.usersservice.model.StateRegion;
import com.rcf.usersservice.repository.StateRegionRepository;
import com.rcf.usersservice.service.StateRegionService;
import com.rcf.usersservice.util.StateRegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateRegionServiceImpl implements StateRegionService {
    private final StateRegionRepository stateRegionRepository;
    private final StateRegionMapper stateRegionMapper;

    @Override
    public List<StateRegionResponse> findAll() {
        return stateRegionMapper.toDtoList(stateRegionRepository.findAll());
    }

    @Override
    public StateRegionResponse findById(Long id) {
        return stateRegionMapper.toDto(stateRegionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("State/Region not found with id: " + id)
        ));
    }

    @Override
    public StateRegionResponse findByName(String name) {
        return stateRegionMapper.toDto(stateRegionRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFound("State/Region not found with name: " + name)
        ));
    }

    @Override
    public StateRegionResponse createStateRegion(StateRegionRequest stateRegionRequest) {
        return stateRegionMapper.toDto(stateRegionRepository.save(stateRegionMapper.toEntity(stateRegionRequest)));
    }

    @Override
    public StateRegionResponse updateStateRegion(Long id, StateRegionRequest stateRegionRequest) {
        StateRegion stateRegionFound = stateRegionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("State/Region not found with id: " + id)
        );
        stateRegionFound.setName(stateRegionRequest.name());
        stateRegionFound.setCountry(stateRegionMapper.toEntity(stateRegionRequest).getCountry());
        return stateRegionMapper.toDto(stateRegionRepository.save(stateRegionFound));
    }

    @Override
    public void deleteStateRegion(Long id) {
        StateRegion stateRegionFound = stateRegionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("State/Region not found with id: " + id)
        );
        stateRegionRepository.delete(stateRegionFound);
    }
}
