package com.rcf.usersservice.util;

import com.rcf.usersservice.dto.CountryRequest;
import com.rcf.usersservice.dto.CountryResponse;
import com.rcf.usersservice.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    Country toEntity(CountryRequest countryRequest);
    CountryResponse toDto(Country country);
    List<CountryResponse> toDtoList(List<Country> countryList);
}
