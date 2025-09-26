package com.rcf.usersservice.util;

import com.rcf.usersservice.dto.StateRegionRequest;
import com.rcf.usersservice.dto.StateRegionResponse;
import com.rcf.usersservice.model.StateRegion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StateRegionMapper {
    @Mapping(source = "countryId", target = "country.id")
    StateRegion toEntity(StateRegionRequest stateRegionRequest);
    StateRegionResponse toDto(StateRegion stateRegion);
    List<StateRegionResponse> toDtoList(List<StateRegion> stateRegionList);
}
