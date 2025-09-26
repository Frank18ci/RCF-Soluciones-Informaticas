package com.rcf.usersservice.service;

import com.rcf.usersservice.dto.CountryRequest;
import com.rcf.usersservice.dto.CountryResponse;

import java.util.List;

public interface CountryService {
    List<CountryResponse> getAllCountries();
    CountryResponse getCountryById(Long id);
    CountryResponse getCountryByCode(String code);
    CountryResponse saveCountry(CountryRequest countryRequest);
    CountryResponse updateCountry(Long id, CountryRequest countryRequest);
    void deleteCountry(Long id);
}
