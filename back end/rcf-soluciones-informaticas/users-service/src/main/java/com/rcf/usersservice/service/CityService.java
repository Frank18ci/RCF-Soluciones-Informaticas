package com.rcf.usersservice.service;

import com.rcf.usersservice.dto.CityRequest;
import com.rcf.usersservice.dto.CityResponse;
import com.rcf.usersservice.model.City;

import java.util.List;

public interface CityService {
    List<CityResponse> getAllCities();
    CityResponse getCityById(Long id);
    CityResponse getCityByName(String name);
    CityResponse saveCity(CityRequest cityRequest);
    CityResponse updateCity(Long id, CityRequest cityRequest);
    void deleteCity(Long id);
}
