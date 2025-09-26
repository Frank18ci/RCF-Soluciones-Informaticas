package com.rcf.usersservice.service.impl;

import com.rcf.usersservice.dto.CountryRequest;
import com.rcf.usersservice.dto.CountryResponse;
import com.rcf.usersservice.exception.ResourceNotFound;
import com.rcf.usersservice.model.Country;
import com.rcf.usersservice.repository.CountryRepository;
import com.rcf.usersservice.service.CountryService;
import com.rcf.usersservice.util.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryResponse> getAllCountries() {
        return countryMapper.toDtoList(countryRepository.findAll());
    }

    @Override
    public CountryResponse getCountryById(Long id) {
        return countryMapper.toDto(countryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Country not found with id: " + id)
        ));
    }

    @Override
    public CountryResponse getCountryByCode(String code) {
        return countryMapper.toDto(countryRepository.findByCode(code).orElseThrow(
                () -> new ResourceNotFound("Country not found with code: " + code)
        ));
    }

    @Override
    public CountryResponse saveCountry(CountryRequest countryRequest) {
        return countryMapper.toDto(countryRepository.save(countryMapper.toEntity(countryRequest)));
    }

    @Override
    public CountryResponse updateCountry(Long id, CountryRequest countryRequest) {
        Country countryFound = countryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Country not found with id: " + id)
        );
        countryFound.setName(countryRequest.name());
        countryFound.setCode(countryRequest.code());

        return countryMapper.toDto(countryRepository.save(countryFound));
    }

    @Override
    public void deleteCountry(Long id) {
        Country countryFound = countryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Country not found with id: " + id)
        );
        countryRepository.delete(countryFound);
    }
}
