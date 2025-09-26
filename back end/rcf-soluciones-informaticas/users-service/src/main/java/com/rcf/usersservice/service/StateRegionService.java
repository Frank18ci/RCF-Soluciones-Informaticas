package com.rcf.usersservice.service;

import com.rcf.usersservice.dto.StateRegionRequest;
import com.rcf.usersservice.dto.StateRegionResponse;
import com.rcf.usersservice.model.StateRegion;

import java.util.List;

public interface StateRegionService {
    List<StateRegionResponse> findAll();
    StateRegionResponse findById(Long id);
    StateRegionResponse findByName(String name);
    StateRegionResponse createStateRegion(StateRegionRequest stateRegionRequest);
    StateRegionResponse updateStateRegion(Long id, StateRegionRequest stateRegionRequest);
    void deleteStateRegion(Long id);
}
