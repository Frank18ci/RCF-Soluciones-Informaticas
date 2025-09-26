package com.rcf.usersservice.service.impl;

import com.rcf.usersservice.dto.CityRequest;
import com.rcf.usersservice.dto.CityResponse;
import com.rcf.usersservice.exception.ResourceNotFound;
import com.rcf.usersservice.model.City;
import com.rcf.usersservice.repository.CityRepository;
import com.rcf.usersservice.service.CityService;
import com.rcf.usersservice.util.CityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityResponse> getAllCities() {
        return cityMapper.toDtoList(cityRepository.findAll());
    }

    @Override
    public CityResponse getCityById(Long id) {
        return cityMapper.toDto(cityRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("City not found with id: " + id)
        ));
    }

    @Override
    public CityResponse getCityByName(String name) {
        return cityMapper.toDto(cityRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFound("City not found with name: " + name)
        ));
    }

    @Override
    public CityResponse saveCity(CityRequest cityRequest) {
        return cityMapper.toDto(cityRepository.save(cityMapper.toEntity(cityRequest)));
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest cityRequest) {
        City cityFound = cityRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("City not found with id: " + id)
        );
        cityFound.setName(cityRequest.name());
        cityFound.setStateRegion(cityMapper.toEntity(cityRequest).getStateRegion());
        return cityMapper.toDto(cityRepository.save(cityFound));
    }

    @Override
    public void deleteCity(Long id) {
        City cityFound = cityRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("City not found with id: " + id)
        );
        cityRepository.delete(cityFound);
    }
}
