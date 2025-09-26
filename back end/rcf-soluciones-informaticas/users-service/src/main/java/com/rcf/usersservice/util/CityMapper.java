package com.rcf.usersservice.util;

import com.rcf.usersservice.dto.CityRequest;
import com.rcf.usersservice.dto.CityResponse;
import com.rcf.usersservice.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StateRegionMapper.class})
public interface CityMapper {
    @Mapping(source = "stateRegionId", target = "stateRegion.id")
    City toEntity(CityRequest cityRequest);
    CityResponse toDto(City city);
    List<CityResponse> toDtoList(List<City> cityList);
}
